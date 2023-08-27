package com.example.dgpayscase.data.repository.local

import androidx.lifecycle.LiveData
import com.example.dgpayscase.data.dao.BasketDao
import com.example.dgpayscase.data.dao.ProductDao
import com.example.dgpayscase.data.dao.TransactionDao
import com.example.dgpayscase.model.Basket
import com.example.dgpayscase.model.Product
import com.example.dgpayscase.model.Transaction
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class LocalRepositoryImpl @Inject constructor(
    private val basketDao: BasketDao,
    private val productDao: ProductDao,
    private val transactionDao: TransactionDao,
) : LocalRepository {

    override suspend fun insert(basketRoom: Basket) {
        basketDao.insert(basketRoom)
    }

    override suspend fun insert(productRoom: Product) {
        productDao.insert(productRoom)
    }

    override suspend fun insert(transactionRoom: Transaction) {
        transactionDao.insert(transactionRoom)
    }

    override suspend fun update(basketRoom: Basket) {
        basketDao.update(basketRoom)
    }

    override suspend fun update(productRoom: Product) {
        productDao.update(productRoom)
    }

    override suspend fun update(transactionRoom: Transaction) {
        transactionDao.update(transactionRoom)
    }

    override suspend fun nukeTableBasket() {
        basketDao.nukeTableBasket()
    }

    override suspend fun getAllBasket(): List<Basket> {
        return basketDao.getAllBasket()
    }

    override fun getCurrentBasket(): LiveData<Basket> {
        return basketDao.getCurrentBasket()
    }

    override fun getCurrentBasketSync(): Basket? {
        return basketDao.getCurrentBasketSync()
    }

    override suspend fun nukeTableProduct() {
        productDao.nukeTableProduct()
    }

    override suspend fun getAllProduct(): List<Product> {
        return productDao.getAllProduct()
    }

    override suspend fun nukeTableTransaction() {
        transactionDao.nukeTableTransaction()
    }

    override suspend fun getAllTransaction(): List<Transaction> {
        return transactionDao.getAllTransaction()
    }
}