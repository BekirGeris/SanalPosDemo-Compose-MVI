package com.example.dgpayscase.data.repository.local

import androidx.lifecycle.LiveData
import com.example.dgpayscase.model.dto.BasketRoom
import com.example.dgpayscase.model.dto.ProductRoom
import com.example.dgpayscase.model.dto.TransactionRoom

interface LocalRepository {

    suspend fun insert(basketRoom: BasketRoom)

    suspend fun update(basketRoom: BasketRoom)

    suspend fun nukeTableBasket()

    suspend fun getAllBasket() : List<BasketRoom>

    fun getCurrentBasket(): LiveData<BasketRoom>

    fun getCurrentBasketSync(): BasketRoom?

    suspend fun insert(productRoom: ProductRoom)

    suspend fun update(productRoom: ProductRoom)

    suspend fun nukeTableProduct()

    suspend fun getAllProduct() : List<ProductRoom>

    suspend fun insert(transactionRoom: TransactionRoom)

    suspend fun update(transactionRoom: TransactionRoom)

    suspend fun nukeTableTransaction()

    suspend fun getAllTransaction() : List<TransactionRoom>
}