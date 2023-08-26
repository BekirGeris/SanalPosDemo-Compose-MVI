package com.example.dgpayscase.util

object Constants {

    const val SALE = 1
    const val VOID = 2

    enum class Status(val type: Int) {
        FAILED(-1),
        SUCCESS(0)
    }
}