package com.example.dgpayscase.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.dgpayscase.data.repository.AppRepository
import com.example.dgpayscase.model.Basket
import com.example.dgpayscase.model.Product
import com.example.dgpayscase.view.MainContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(private val appRepository: AppRepository) : BaseViewModel<MainContract.Event<MainContract.BasketEvent>, MainContract.State<MainContract.BasketState>, MainContract.Effect>() {

    lateinit var basket: Basket

    init {
        Log.d("bekbek", "basket init")
        setState { MainContract.State(MainContract.BasketState.Loading) }
        appRepository.localRepositoryImpl.getCurrentBasket().observeForever {
            Log.d("bekbek", "pbserve basket: $it")
            if (it != null) {
                basket = it
                setState { MainContract.State(MainContract.BasketState.CurrentBasket(it)) }
            } else {
                setState { MainContract.State(MainContract.BasketState.Error("Basket Null")) }
            }
        }
    }

    override fun createInitialState(): MainContract.State<MainContract.BasketState> {
        return MainContract.State(
            value = MainContract.BasketState.Idle
        )
    }

    override fun handleEvent(event: MainContract.Event<MainContract.BasketEvent>) {
        when (event.value) {
            is MainContract.BasketEvent.AddProduct -> addProduct(event.value.product)
        }
    }

    private fun addProduct(product: Product) {
        viewModelScope.launch {
            setState { MainContract.State(MainContract.BasketState.Loading) }
            appRepository.localRepositoryImpl.update(basket)
        }
    }
}