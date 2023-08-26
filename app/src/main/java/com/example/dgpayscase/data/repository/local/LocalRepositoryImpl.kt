package com.example.dgpayscase.data.repository.local

import androidx.lifecycle.LiveData
import com.example.dgpayscase.data.dao.BasketDao
import com.example.dgpayscase.data.dao.ProductDao
import com.example.dgpayscase.data.dao.TransactionDao
import com.example.dgpayscase.model.dto.BasketRoom
import com.example.dgpayscase.model.dto.ProductRoom
import com.example.dgpayscase.model.dto.TransactionRoom
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class LocalRepositoryImpl @Inject constructor(
    private val basketDao: BasketDao,
    private val productDao: ProductDao,
    private val transactionDao: TransactionDao,
) : LocalRepository {

    override suspend fun insert(basketRoom: BasketRoom) {
        basketDao.insert(basketRoom)
    }

    override suspend fun insert(productRoom: ProductRoom) {
        productDao.insert(productRoom)
    }

    override suspend fun insert(transactionRoom: TransactionRoom) {
        transactionDao.insert(transactionRoom)
    }

    override suspend fun update(basketRoom: BasketRoom) {
        basketDao.update(basketRoom)
    }

    override suspend fun update(productRoom: ProductRoom) {
        productDao.update(productRoom)
    }

    override suspend fun update(transactionRoom: TransactionRoom) {
        transactionDao.update(transactionRoom)
    }

    override suspend fun nukeTableBasket() {
        basketDao.nukeTableBasket()
    }

    override suspend fun getAllBasket(): List<BasketRoom> {
        return basketDao.getAllBasket()
    }

    override fun getCurrentBasket(): LiveData<BasketRoom> {
        return basketDao.getCurrentBasket()
    }

    override fun getCurrentBasketSync(): BasketRoom? {
        return basketDao.getCurrentBasketSync()
    }

    override suspend fun nukeTableProduct() {
        productDao.nukeTableProduct()
    }

    override suspend fun getAllProduct(): List<ProductRoom> {
        return productDao.getAllProduct()
    }

    override suspend fun nukeTableTransaction() {
        transactionDao.nukeTableTransaction()
    }

    override suspend fun getAllTransaction(): List<TransactionRoom> {
        return transactionDao.getAllTransaction()
    }
}