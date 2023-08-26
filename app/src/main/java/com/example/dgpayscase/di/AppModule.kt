package com.example.dgpayscase.di

import com.example.dgpayscase.data.repository.AppRepository
import com.example.dgpayscase.data.repository.firebase.FirebaseRepository
import com.example.dgpayscase.data.repository.local.LocalRepository
import com.example.dgpayscase.data.repository.remote.RemoteRepository
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
        localRepository: LocalRepository,
        remoteRepository: RemoteRepository,
        firebaseRepository: FirebaseRepository
    ) = AppRepository(localRepository, remoteRepository, firebaseRepository)

}