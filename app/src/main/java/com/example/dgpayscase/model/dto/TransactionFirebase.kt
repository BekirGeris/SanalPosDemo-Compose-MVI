package com.example.dgpayscase.model.dto

data class TransactionFirebase(
    var key: String,
    var txnType: Int,
    var txnId: String,
    var txnDate: String,
    var totalAmount: String
)