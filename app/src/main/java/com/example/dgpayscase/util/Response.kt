package com.example.dgpayscase.util

import com.google.gson.annotations.SerializedName

class Response<T> {
    @SerializedName("success")
    var success: Boolean = false
    @SerializedName("message")
    var message: String = ""
    @SerializedName("data")
    var data: T? = null
}