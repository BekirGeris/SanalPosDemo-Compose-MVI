package com.example.dgpayscase.model

data class Transaction(
    var txnType: Int,
    var txnId: String,
    var txnDate: String,
    var totalAmount: String
)
