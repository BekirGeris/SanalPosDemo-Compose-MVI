package com.example.dgpayscase.model.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "product")
data class ProductRoom(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id: Int,
    var name: String,
    var amount: String,
    var imageBitmap: String,
    var basketCount: Int = 0
)
