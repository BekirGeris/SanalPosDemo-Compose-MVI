package com.example.dgpayscase.di

import android.content.Context
import androidx.room.Room
import com.example.dgpayscase.data.dao.BasketDao
import com.example.dgpayscase.data.dao.ProductDao
import com.example.dgpayscase.data.dao.TransactionDao
import com.example.dgpayscase.data.db.AppDatabase
import com.example.dgpayscase.data.repository.local.LocalRepository
import com.example.dgpayscase.data.repository.local.LocalRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideLocalRepositoryImpl(
        basketDao: BasketDao,
        productDao: ProductDao,
        transactionDao: TransactionDao
    ): LocalRepositoryImpl = LocalRepositoryImpl(basketDao, productDao, transactionDao)

    @Singleton
    @Provides
    fun provideBasketDao(appDatabase: AppDatabase) = appDatabase.basketDao()

    @Singleton
    @Provides
    fun provideProductDao(appDatabase: AppDatabase) = appDatabase.productDao()

    @Singleton
    @Provides
    fun provideTransactionDao(appDatabase: AppDatabase) = appDatabase.transactionDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(app, AppDatabase::class.java, "begers")
            .allowMainThreadQueries()
            .addMigrations(*AppDatabase.getMigrations())
            .build()
}