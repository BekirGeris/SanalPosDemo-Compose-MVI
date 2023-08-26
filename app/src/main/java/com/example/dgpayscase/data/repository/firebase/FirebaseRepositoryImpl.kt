package com.example.dgpayscase.data.repository.firebase

import androidx.lifecycle.MutableLiveData
import com.example.dgpayscase.model.dto.TransactionFirebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class FirebaseRepositoryImpl @Inject constructor(firebaseDatabase: FirebaseDatabase) : FirebaseRepository {
    var transactionFirebaseList = MutableLiveData<List<TransactionFirebase>>()
    private var refUser: DatabaseReference

    init {
        transactionFirebaseList = MutableLiveData()
        refUser = firebaseDatabase.getReference("transactions")
    }

    override fun getAllTransactions() {
        refUser.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<TransactionFirebase>()

                for (c in snapshot.children) {
                    val user = c.getValue(TransactionFirebase::class.java)

                    user?.let {
                        it.key = c.key!!
                        list.add(it)
                    }
                }

                transactionFirebaseList.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun saveTransaction(transactionFirebase: TransactionFirebase) {
        refUser.push().setValue(transactionFirebase)
    }

    override fun updateTransaction(transactionFirebase: TransactionFirebase) {
        val data = HashMap<String, Any>()
        data["txnType"] = transactionFirebase.txnType
        data["txnId"] = transactionFirebase.txnId
        data["txnDate"] = transactionFirebase.txnDate
        data["totalAmount"] = transactionFirebase.totalAmount
        refUser.child(transactionFirebase.key).updateChildren(data)
    }

    override fun deleteTransaction(transactionFirebase: TransactionFirebase) {
        refUser.child(transactionFirebase.key).removeValue()
    }
}