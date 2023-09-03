package com.example.dgpayscase.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.dgpayscase.data.repository.AppRepository
import com.example.dgpayscase.model.Product
import com.example.dgpayscase.view.MainContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val appRepository: AppRepository) : BaseViewModel<MainContract.Event<MainContract.ProductEvent>, MainContract.State<MainContract.ProductState>, MainContract.Effect>() {
    override fun createInitialState(): MainContract.State<MainContract.ProductState> {
        return MainContract.State(
            value = MainContract.ProductState.Idle
        )
    }

    override fun handleEvent(event: MainContract.Event<MainContract.ProductEvent>) {
        Log.d("bekbek", "handleEvent")
        when (event.value) {
            is MainContract.ProductEvent.SaveProduct -> saveData(event.value.product)
            is MainContract.ProductEvent.UpdateProduct -> updateProduct(event.value.product)
        }
    }

    private fun saveData(product: Product) {
        viewModelScope.launch {
            try {
                setState { MainContract.State(MainContract.ProductState.Loading) }
                val response = appRepository.remoteRepositoryImpl.addProduct(product)
                if (response.success) {
                    setState { MainContract.State(MainContract.ProductState.SaveProduct) }
                } else {
                    setState { MainContract.State(MainContract.ProductState.Error(response.message)) }
                }
            } catch (e:Exception) {
                setState { MainContract.State(MainContract.ProductState.Error(e.localizedMessage)) }
            }
        }
    }

    private fun updateProduct(product: Product) {
        viewModelScope.launch {
            try {
                setState { MainContract.State(MainContract.ProductState.Loading) }
                val response = appRepository.remoteRepositoryImpl.updateProduct(product)
                if (response.success) {
                    setState { MainContract.State(MainContract.ProductState.UpdateProduct) }
                } else {
                    setState { MainContract.State(MainContract.ProductState.Error(response.message)) }
                }
            } catch (e:Exception) {
                setState { MainContract.State(MainContract.ProductState.Error(e.localizedMessage)) }
            }
        }
    }
}