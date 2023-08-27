package com.example.dgpayscase.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.dgpayscase.model.Product

@Dao
interface ProductDao {

    @Insert
    suspend fun insert(product: Product)

    @Update
    suspend fun update(product: Product)

    @Query("delete from product")
    suspend fun nukeTableProduct()

    @Query("select * from product")
    suspend fun getAllProduct() : List<Product>
}