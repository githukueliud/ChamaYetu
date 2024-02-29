package com.example.chamayetu.di

import com.example.chamayetu.data.repository.AuthRepository
import com.example.chamayetu.data.repository.AuthRepositoryImpl
import com.example.chamayetu.data.repository.DatabaseRepository
import com.example.chamayetu.data.repository.DatabaseRepositoryImpl
import com.example.chamayetu.data.repository.FormValidationRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

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


}