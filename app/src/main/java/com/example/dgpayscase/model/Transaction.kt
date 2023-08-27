package com.example.dgpayscase.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "transaction")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("roomId")
    var roomId: Int,
    var key: String,
    var txnType: Int,
    var txnId: String,
    var txnDate: String,
    var totalAmount: String
)
