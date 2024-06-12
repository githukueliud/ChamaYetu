package com.example.chamayetu.data.network

import com.example.chamayetu.data.model.MyUser
import kotlinx.coroutines.flow.Flow

interface ApiRepository {

    suspend fun registerUser(myUser: MyUser): Flow<MyUser>
}