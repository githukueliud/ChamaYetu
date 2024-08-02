package com.example.chamayetu.data.network

import android.content.Context
import android.content.SharedPreferences


class TokenManager(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        prefs.edit().putString("JWT_TOKEN", token).apply()
    }

    fun getToken(): String? {
        return prefs.getString("JWT_TOKEN", null)
    }
}


