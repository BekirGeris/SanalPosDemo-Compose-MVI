package com.example.dgpayscase.viewmodel

import androidx.lifecycle.ViewModel
import com.example.dgpayscase.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {

    init {
        appRepository.firebaseRepository.getAllTransactions()
    }
}