package com.example.dgpayscase.model.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "basket")
class BasketRoom(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id: Int? = null
)