package com.example.dgpayscase.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.dgpayscase.model.Basket

@Dao
interface BasketDao {

    @Insert
    suspend fun insert(basket: Basket)

    @Update
    suspend fun update(basket: Basket)

    @Query("delete from basket")
    suspend fun nukeTableBasket()

    @Query("select * from basket")
    suspend fun getAllBasket() : List<Basket>

    @Query("select distinct * from basket order by id desc Limit 1")
    fun getCurrentBasket(): LiveData<Basket>

    @Query("select distinct * from basket  order by id desc Limit 1")
    fun getCurrentBasketSync(): Basket?
}