package com.example.dgpayscase.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "basket")
class Basket(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("roomId")
    var roomId: Int? = null
)
