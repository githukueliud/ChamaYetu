package com.example.chamayetu.presentation.login

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

object UserPreferences {
    val EMAIL = stringPreferencesKey("email")
    val FIRST_NAME = stringPreferencesKey("first_name")
    val LAST_NAME = stringPreferencesKey("last_name")
    val PHONE_NUMBER = stringPreferencesKey("phone_number")
    val ROLE = stringPreferencesKey("role")
    val PASSWORD = stringPreferencesKey("password")
    val USER_ID = stringPreferencesKey("user_id")
}