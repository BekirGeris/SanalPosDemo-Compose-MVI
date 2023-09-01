package com.example.dgpayscase.view

import com.example.dgpayscase.model.Product
import com.example.dgpayscase.model.Transaction
import com.example.dgpayscase.ui.UiEffect
import com.example.dgpayscase.ui.UiEvent
import com.example.dgpayscase.ui.UiState
import com.example.dgpayscase.util.Response

class MainContract {

    // Events that user performed
    sealed class Event : UiEvent {
        object FetchProduct : Event()
    }

    // Ui View States
    class State<T>(val state: T) : UiState

    // View State that related to Random Number
    sealed class HomeState {
        object Idle : HomeState()
        object Loading : HomeState()
        data class Products(val data: ArrayList<Product>) : HomeState()
        data class Error(val error: String?) : HomeState()
    }

    sealed class TransactionState {
        object Idle : TransactionState()
        object Loading : TransactionState()
        data class Transactions(val data: ArrayList<Transaction>) : TransactionState()
        data class Error(val error: String?) : TransactionState()
    }

    sealed class ProductState {
        object Idle : ProductState()
        object Loading : ProductState()
        data class Products(val data: ArrayList<Product>) : ProductState()
        data class Error(val error: String?) : ProductState()
    }

    // Side effects
    sealed class Effect : UiEffect {
        data class ShowToast(val message: String) : Effect()
    }
}