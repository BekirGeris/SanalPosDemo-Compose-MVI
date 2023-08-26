package com.example.dgpayscase.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.dgpayscase.model.dto.ProductRoom

@Dao
interface ProductDao {

    @Insert
    suspend fun insert(productRoom: ProductRoom)

    @Update
    suspend fun update(productRoom: ProductRoom)

    @Query("delete from product")
    suspend fun nukeTableProduct()

    @Query("select * from product")
    suspend fun getAllProduct() : List<ProductRoom>
}