package com.example.chamayetu.di

import com.example.chamayetu.data.repository.AuthRepository
import com.example.chamayetu.data.repository.AuthRepositoryImpl
import com.example.chamayetu.data.repository.DatabaseRepository
import com.example.chamayetu.data.repository.DatabaseRepositoryImpl
import com.example.chamayetu.data.repository.FormValidationRepository
import com.google.firebase.BuildConfig
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    private const val TIMEOUT = 30L


    @Provides
    @Singleton
    fun provideFormValidationRepository() = FormValidationRepository()


    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()



    @Provides
    @Singleton
    fun provideAuthRepositoryImpl(firebaseAuth: FirebaseAuth): AuthRepository{
        return AuthRepositoryImpl(firebaseAuth)
    }


    @Provides
    @Singleton
    fun providesDatabaseRepositoryImpl(): DatabaseRepository {
        return DatabaseRepositoryImpl()
    }


    @Provides
    @Singleton
    fun provideOkhttp(): OkHttpClient = OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG) {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
        connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        readTimeout(TIMEOUT, TimeUnit.SECONDS)
    }.build()



    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder().apply {
        baseUrl("http://192.168.0.105:8080")
        client(okHttpClient)
        addConverterFactory(MoshiConverterFactory.create())
    }.build()


}