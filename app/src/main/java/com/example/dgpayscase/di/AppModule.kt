package com.example.dgpayscase.di

import com.example.dgpayscase.data.repository.AppRepository
import com.example.dgpayscase.data.repository.local.LocalRepositoryImpl
import com.example.dgpayscase.data.repository.remote.RemoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppRepository(
        localRepositoryImpl: LocalRepositoryImpl,
        remoteRepositoryImpl: RemoteRepositoryImpl
    ) = AppRepository(localRepositoryImpl, remoteRepositoryImpl)

}