package com.example.chamayetu.data.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {

    private const val BASE_URL = "http://192.168.0.101:8080"
    private var token: String? = null

    fun setToken(newToken: String) {
        token = newToken
        // Update the Retrofit instance for protected requests
    }

    private fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getInstanceForProtected(): Retrofit {
        val currentToken = token ?: throw IllegalStateException("Token not set")
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor { currentToken })
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val signupApi: SignupApi by lazy { getInstance().create(SignupApi::class.java) }
    val loginApi: LoginApi by lazy { getInstance().create(LoginApi::class.java) }
    val projectsApi: ProjectsApi by lazy { getInstanceForProtected().create(ProjectsApi::class.java) }


}
