package com.example.dgpayscase.data.repository.local

import androidx.lifecycle.LiveData
import com.example.dgpayscase.model.Basket
import com.example.dgpayscase.model.Product
import com.example.dgpayscase.model.Transaction

interface LocalRepository {

    suspend fun insert(basket: Basket)

    suspend fun update(basket: Basket)

    suspend fun nukeTableBasket()

    suspend fun getAllBasket() : List<Basket>

    fun getCurrentBasket(): LiveData<Basket>

    fun getCurrentBasketSync(): Basket?

    suspend fun insert(product: Product)

    suspend fun update(product: Product)

    suspend fun nukeTableProduct()

    suspend fun getAllProduct() : List<Product>

    suspend fun insert(transaction: Transaction)

    suspend fun update(transaction: Transaction)

    suspend fun nukeTableTransaction()

    suspend fun getAllTransaction() : List<Transaction>
}