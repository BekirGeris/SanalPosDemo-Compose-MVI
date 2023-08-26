package com.example.dgpayscase.data.repository.firebase

import com.example.dgpayscase.model.dto.TransactionFirebase

interface FirebaseRepository {

    fun getAllTransactions()

    fun saveTransaction(transactionFirebase: TransactionFirebase)

    fun updateTransaction(transactionFirebase: TransactionFirebase)

    fun deleteTransaction(transactionFirebase: TransactionFirebase)
}