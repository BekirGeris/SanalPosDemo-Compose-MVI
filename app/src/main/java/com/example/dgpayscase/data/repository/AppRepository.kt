package com.example.dgpayscase.data.repository

import com.example.dgpayscase.data.repository.firebase.FirebaseRepository
import com.example.dgpayscase.data.repository.local.LocalRepository
import com.example.dgpayscase.data.repository.remote.RemoteRepository
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class AppRepository @Inject constructor(val localRepository: LocalRepository, val remoteRepository: RemoteRepository, val firebaseRepository: FirebaseRepository)