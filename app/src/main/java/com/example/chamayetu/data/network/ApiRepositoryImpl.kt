package com.example.chamayetu.data.network

import com.example.chamayetu.data.model.MyUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ApiRepositoryImpl(private val services: ApiServices): ApiRepository {
    override suspend fun registerUser(myUser: MyUser): Flow<MyUser> = flow {
        emit(services.registerUser(myUser))
    }
}