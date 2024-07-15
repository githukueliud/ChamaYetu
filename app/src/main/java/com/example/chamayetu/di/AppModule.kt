package com.example.chamayetu.di

import android.app.Application
import android.content.Context
import com.example.chamayetu.data.local.manager.LocalUserManagerImpl
import com.example.chamayetu.data.network.ApiRepository
import com.example.chamayetu.data.network.ApiRepositoryImpl
import com.example.chamayetu.data.network.ApiServices
import com.example.chamayetu.domain.manager.LocalUserManager
import com.example.chamayetu.domain.usecases.app_entry.AppEntryUseCases
import com.example.chamayetu.domain.usecases.app_entry.ReadAppEntry
import com.example.chamayetu.domain.usecases.app_entry.SaveAppEntry
import com.example.chamayetu.presentation.login.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)


    @Provides
    @Singleton
    fun provideEntryAppUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )


    @InstallIn(SingletonComponent::class)
    @Module
    class AppRepoModule {
        @Provides
        fun providesApiRepository(apiServices: ApiServices): ApiRepository =
            ApiRepositoryImpl(apiServices)
    }

    @InstallIn(SingletonComponent::class)
    @Module
    class ApiServiceModule {
        @Provides
        fun providesApiServices(retrofit: Retrofit): ApiServices = ApiServices.create(retrofit)
    }



    @Provides
    @Singleton
    fun provideUserRepository(
        @ApplicationContext context: Context
    ): UserRepository {
        return UserRepository(context)
    }

}