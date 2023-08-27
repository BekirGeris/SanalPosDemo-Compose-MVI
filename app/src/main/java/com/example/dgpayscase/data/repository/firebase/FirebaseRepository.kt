package com.example.dgpayscase.data.repository.firebase

import com.example.dgpayscase.model.Transaction

interface FirebaseRepository {

    fun getAllTransactions()

    fun saveTransaction(transaction: Transaction)

    fun updateTransaction(transaction: Transaction)

    fun deleteTransaction(transaction: Transaction)
}