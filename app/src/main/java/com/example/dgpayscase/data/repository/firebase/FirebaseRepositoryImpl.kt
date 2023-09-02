package com.example.dgpayscase.data.repository.firebase

import androidx.lifecycle.MutableLiveData
import com.example.dgpayscase.model.Transaction
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class FirebaseRepositoryImpl @Inject constructor(firebaseDatabase: FirebaseDatabase) : FirebaseRepository {
    var transactionList = MutableLiveData<ArrayList<Transaction>>()
    private var refUser: DatabaseReference

    init {
        refUser = firebaseDatabase.getReference("transactions")
    }

    override fun getAllTransactions() {
        refUser.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<Transaction>()

                for (c in snapshot.children) {
                    val user = c.getValue(Transaction::class.java)

                    user?.let {
                        it.key = c.key!!
                        list.add(it)
                    }
                }

                transactionList.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                transactionList.value = arrayListOf()
            }
        })
    }

    override fun saveTransaction(transaction: Transaction) {
        refUser.push().setValue(transaction)
    }

    override fun updateTransaction(transaction: Transaction) {
        val data = HashMap<String, Any>()
        data["txnType"] = transaction.txnType
        data["txnId"] = transaction.txnId
        data["txnDate"] = transaction.txnDate
        data["totalAmount"] = transaction.totalAmount
        refUser.child(transaction.key).updateChildren(data)
    }

    override fun deleteTransaction(transaction: Transaction) {
        refUser.child(transaction.key).removeValue()
    }
}