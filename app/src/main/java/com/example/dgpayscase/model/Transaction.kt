package com.example.dgpayscase.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "transaction")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id: Int,
    var txnType: Int,
    var txnId: String,
    var txnDate: String,
    var totalAmount: String
)
