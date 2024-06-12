package com.example.chamayetu.data.network

import com.example.chamayetu.data.model.MyUser
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServices {

    @POST("/register/user")
    suspend fun registerUser(@Body myUser: MyUser): MyUser





    companion object Factory {
        fun create(retrofit: Retrofit): ApiServices = retrofit.create(ApiServices::class.java)
    }
}