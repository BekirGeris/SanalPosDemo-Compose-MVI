package com.example.dgpayscase.data.repository

import com.example.dgpayscase.data.repository.firebase.FirebaseRepositoryImpl
import com.example.dgpayscase.data.repository.local.LocalRepositoryImpl
import com.example.dgpayscase.data.repository.remote.RemoteRepositoryImpl
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class AppRepository @Inject constructor(val localRepositoryImpl: LocalRepositoryImpl, val remoteRepositoryImpl: RemoteRepositoryImpl, val firebaseRepositoryImpl: FirebaseRepositoryImpl)