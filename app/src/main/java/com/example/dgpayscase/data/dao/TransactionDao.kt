package com.example.dgpayscase.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.dgpayscase.model.Transaction

@Dao
interface TransactionDao {

    @Insert
    suspend fun insert(transaction: Transaction)

    @Update
    suspend fun update(transaction: Transaction)

    @Query("delete from `transaction`")
    suspend fun nukeTableTransaction()

    @Query("select * from `transaction`")
    suspend fun getAllTransaction() : List<Transaction>
}