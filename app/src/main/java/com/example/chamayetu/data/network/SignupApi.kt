package com.example.chamayetu.data.network

import com.example.chamayetu.data.model.MyUser
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignupApi {


    @POST("/auth/register")
    suspend fun signupUser(@Body myUser: MyUser): Response<SignupResponse>



}