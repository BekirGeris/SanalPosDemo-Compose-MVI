package com.example.dgpayscase.viewmodel

import com.example.dgpayscase.data.repository.AppRepository
import com.example.dgpayscase.model.Transaction
import com.example.dgpayscase.view.MainContract
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(private val appRepository: AppRepository) : BaseViewModel<MainContract.Event<MainContract.TransactionEvent>, MainContract.State<MainContract.TransactionState>, MainContract.Effect>() {

    init {
        appRepository.firebaseRepositoryImpl.transactionList.observeForever {
            if (it != null && it.isNotEmpty()) {
                setState { MainContract.State(MainContract.TransactionState.Transactions(it)) }
            } else {
                setState { MainContract.State(MainContract.TransactionState.Error("Liste Boş")) }
            }
        }
    }

    override fun createInitialState(): MainContract.State<MainContract.TransactionState> {
        return MainContract.State(
            value = MainContract.TransactionState.Idle
        )
    }

    override fun handleEvent(event: MainContract.Event<MainContract.TransactionEvent>) {
        when (event.value) {
            is MainContract.TransactionEvent.FetchData -> fetchData()
            is MainContract.TransactionEvent.SaveTransaction -> saveTransaction(event.value.transaction)
        }
    }

    private fun fetchData() {
        setState { MainContract.State(MainContract.TransactionState.Loading) }
        appRepository.firebaseRepositoryImpl.getAllTransactions()
    }

    private fun saveTransaction(transaction: Transaction) {
        setState { MainContract.State(MainContract.TransactionState.Loading) }
        try {
            appRepository.firebaseRepositoryImpl.saveTransaction(transaction)
        } catch (e: Exception) {
            setState { MainContract.State(MainContract.TransactionState.Error(e.localizedMessage)) }
        }
    }
}