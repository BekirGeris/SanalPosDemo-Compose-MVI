package com.example.dgpayscase.view

import com.example.dgpayscase.model.Basket
import com.example.dgpayscase.model.Product
import com.example.dgpayscase.model.Transaction
import com.example.dgpayscase.ui.UiEffect
import com.example.dgpayscase.ui.UiEvent
import com.example.dgpayscase.ui.UiState

class MainContract {

    class Event<T>(val value: T) : UiEvent

    // Events that user performed
    sealed class HomeEvent {
        object FetchData : HomeEvent()
    }

    sealed class TransactionEvent {
        object FetchData : TransactionEvent()
        data class SaveTransaction(val transaction: Transaction) : TransactionEvent()
    }

    sealed class BasketEvent {
        data class AddProduct(val product: Product) : BasketEvent()
    }

    sealed class ProductEvent {
        object FetchData : ProductEvent()
    }

    // Ui View States
    class State<T>(val value: T) : UiState

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

    sealed class BasketState {
        object Idle : BasketState()
        object Loading : BasketState()
        data class CurrentBasket(val data: Basket) : BasketState()
        data class Error(val error: String?) : BasketState()
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