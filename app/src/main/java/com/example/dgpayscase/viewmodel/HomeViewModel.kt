package com.example.dgpayscase.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.dgpayscase.data.repository.AppRepository
import com.example.dgpayscase.view.MainContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val appRepository: AppRepository) : BaseViewModel<MainContract.Event<MainContract.HomeEvent>, MainContract.State<MainContract.HomeState>, MainContract.Effect>() {

    override fun createInitialState(): MainContract.State<MainContract.HomeState> {
        return MainContract.State(
            value = MainContract.HomeState.Idle
        )
    }

    override fun handleEvent(event: MainContract.Event<MainContract.HomeEvent>) {
        when (event.value) {
            is MainContract.HomeEvent.FetchData -> fetchData()
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            setState { MainContract.State(MainContract.HomeState.Loading) }
            try {
                val data = appRepository.remoteRepositoryImpl.getProducts()
                if (data.success && data.data != null) {
                    setState { MainContract.State(MainContract.HomeState.Products(data.data!!)) }
                } else {
                    setEffect { MainContract.Effect.ShowToast(data.message) }
                    setState { MainContract.State(MainContract.HomeState.Error(data.message)) }
                }
            } catch (e: Exception) {
                setState {
                    setEffect { MainContract.Effect.ShowToast(e.localizedMessage ?: "") }
                    MainContract.State(
                        MainContract.HomeState.Error(e.localizedMessage)
                    )
                }
            }
        }
    }
}