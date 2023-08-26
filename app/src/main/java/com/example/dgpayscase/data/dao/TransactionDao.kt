package com.example.dgpayscase.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.dgpayscase.model.dto.TransactionRoom

@Dao
interface TransactionDao {

    @Insert
    suspend fun insert(transactionRoom: TransactionRoom)

    @Update
    suspend fun update(transactionRoom: TransactionRoom)

    @Query("delete from `transaction`")
    suspend fun nukeTableTransaction()

    @Query("select * from `transaction`")
    suspend fun getAllTransaction() : List<TransactionRoom>
}