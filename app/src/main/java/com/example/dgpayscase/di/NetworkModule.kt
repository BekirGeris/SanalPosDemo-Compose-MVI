package com.example.dgpayscase.di

import android.content.Context
import com.example.dgpayscase.data.repository.firebase.FirebaseRepository
import com.example.dgpayscase.data.repository.firebase.FirebaseRepositoryImpl
import com.example.dgpayscase.data.repository.remote.RemoteRepository
import com.example.dgpayscase.data.repository.remote.RemoteRepositoryImpl
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = ""

    @Singleton
    @Provides
    fun provideRemoteRepository(retrofit: Retrofit): RemoteRepository = retrofit.create(RemoteRepository::class.java)

    @Singleton
    @Provides
    fun provideFirebaseRepository(firebaseDatabase: FirebaseDatabase) : FirebaseRepository = FirebaseRepositoryImpl(firebaseDatabase)

    @Singleton
    @Provides
    fun provideFirebaseDatabase() : FirebaseDatabase = FirebaseDatabase.getInstance()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: Interceptor, httpLoggingInterceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor(httpLoggingInterceptor)
        .build()

    @Singleton
    @Provides
    fun provideHeaderInterceptor(@ApplicationContext app: Context): Interceptor {
        val interceptor = Interceptor {
            val request = it.request().newBuilder()
//                .header("Authorization", "Bearer ${SharedPreferencesUtil.getString(app, Constants.TOKEN_KEY, "")}")
//                .header("lang", Locale.getDefault().language)
                .build()
            it.proceed(request)
        }
        return interceptor
    }

    @Singleton
    @Provides
    fun provideHTTPLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Singleton
    @Provides
    fun provideRemoteRepositoryImpl(remoteRepository: RemoteRepository) = RemoteRepositoryImpl(remoteRepository)
}