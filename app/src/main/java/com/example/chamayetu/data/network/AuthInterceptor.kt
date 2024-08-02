package com.example.chamayetu.data.network

import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor(private val authTokenProvider: () -> String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val authToken = authTokenProvider()

        // Add the authorization header if the token is not empty
        val requestWithAuth = originalRequest.newBuilder()
            .apply {
                if (authToken.isNotEmpty()) {
                    header("Authorization", "Bearer $authToken")
                }
            }
            .build()

        return chain.proceed(requestWithAuth)
    }
}
