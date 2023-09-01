package com.example.dgpayscase.viewmodel

import com.example.dgpayscase.data.repository.AppRepository
import com.example.dgpayscase.view.MainContract
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(val appRepository: AppRepository) : BaseViewModel<MainContract.Event, MainContract.State<MainContract.HomeState>, MainContract.Effect>() {
    override fun createInitialState(): MainContract.State<MainContract.HomeState> {
        TODO("Not yet implemented")
    }

    override fun handleEvent(event: MainContract.Event) {
        TODO("Not yet implemented")
    }

}