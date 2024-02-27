package com.example.chamayetu.data.repository

import com.example.chamayetu.utils.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

public interface AuthRepository {

    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>


    fun registerUser(email: String, password: String): Flow<Resource<AuthResult>>
}