package com.example.dgpayscase.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "product")
data class Product(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("roomId")
    var roomId: Int,
    var remoteId: Int,
    var name: String,
    var amount: String,
    var imageBitmap: String,
    var basketCount: Int = 0
)
