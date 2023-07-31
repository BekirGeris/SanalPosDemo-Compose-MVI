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

    override suspend fun insert(basket: Basket) {
        basketDao.insert(basket)
    }

    override suspend fun insert(product: Product) {
        productDao.insert(product)
    }

    override suspend fun insert(transaction: Transaction) {
        transactionDao.insert(transaction)
    }

    override suspend fun update(basket: Basket) {
        basketDao.update(basket)
    }

    override suspend fun update(product: Product) {
        productDao.update(product)
    }

    override suspend fun update(transaction: Transaction) {
        transactionDao.update(transaction)
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