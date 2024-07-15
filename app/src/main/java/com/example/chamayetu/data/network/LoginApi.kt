package com.example.chamayetu.data.network

import com.example.chamayetu.presentation.login.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {


    @POST("/auth/login")
    suspend fun userLogin(@Body loginRequest: LoginRequest): Response<LoginResponse>

}