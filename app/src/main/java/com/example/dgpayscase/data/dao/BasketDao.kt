package com.example.dgpayscase.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.dgpayscase.model.dto.BasketRoom

@Dao
interface BasketDao {

    @Insert
    suspend fun insert(basketRoom: BasketRoom)

    @Update
    suspend fun update(basketRoom: BasketRoom)

    @Query("delete from basket")
    suspend fun nukeTableBasket()

    @Query("select * from basket")
    suspend fun getAllBasket() : List<BasketRoom>

    @Query("select distinct * from basket order by id desc Limit 1")
    fun getCurrentBasket(): LiveData<BasketRoom>

    @Query("select distinct * from basket  order by id desc Limit 1")
    fun getCurrentBasketSync(): BasketRoom?
}