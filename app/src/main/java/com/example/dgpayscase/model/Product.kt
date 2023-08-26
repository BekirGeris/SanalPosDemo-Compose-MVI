package com.example.dgpayscase.model

data class Product(
    var name: String,
    var amount: String,
    var imageBitmap: String,
    var basketCount: Int = 0
)
