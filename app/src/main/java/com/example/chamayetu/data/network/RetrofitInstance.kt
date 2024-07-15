package com.example.chamayetu.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "http://192.168.0.101:8080"



    private fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val signupApi: SignupApi = getInstance().create(SignupApi::class.java)
    val loginApi: LoginApi = getInstance().create(LoginApi::class.java)

}