package com.example.chamayetu.di

import android.app.Application
import com.example.chamayetu.data.local.manager.LocalUserManagerImpl
import com.example.chamayetu.domain.manager.LocalUserManager
import com.example.chamayetu.domain.usecases.app_entry.AppEntryUseCases
import com.example.chamayetu.domain.usecases.app_entry.ReadAppEntry
import com.example.chamayetu.domain.usecases.app_entry.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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

}