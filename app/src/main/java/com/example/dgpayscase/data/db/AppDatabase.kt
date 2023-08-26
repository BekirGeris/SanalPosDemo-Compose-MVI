package com.example.dgpayscase.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dgpayscase.data.dao.BasketDao
import com.example.dgpayscase.data.dao.ProductDao
import com.example.dgpayscase.data.dao.TransactionDao
import com.example.dgpayscase.model.dto.BasketRoom
import com.example.dgpayscase.model.dto.ProductRoom
import com.example.dgpayscase.model.dto.TransactionRoom

@Database(entities = [BasketRoom::class, ProductRoom::class, TransactionRoom::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun basketDao(): BasketDao
    abstract fun productDao(): ProductDao
    abstract fun transactionDao(): TransactionDao

    companion object {
        fun getMigrations(): Array<AppMigration> {
            return arrayOf(
//                object : AppMigration(1, 2) {
//                    override fun migrate(database: SupportSQLiteDatabase) {
//                        database.execSQL("ALTER TABLE Basket ADD COLUMN viewType INTEGER DEFAULT null")
//                        database.execSQL("ALTER TABLE Basket ADD COLUMN viewId TEXT")
//                    }
//                }
            )
        }
    }
}