package com.example.chamayetu.presentation.login

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.example.chamayetu.data.network.UserDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository (private val context: Context) {

    private val dataStore = context.dataStore

    suspend fun saveUser(user: UserDto) {
        dataStore.edit { preferences ->
            preferences[UserPreferences.EMAIL] = user.email
            preferences[UserPreferences.FIRST_NAME] = user.firstName
            preferences[UserPreferences.LAST_NAME] = user.lastName
            preferences[UserPreferences.PHONE_NUMBER] = user.phoneNumber
            preferences[UserPreferences.ROLE] = user.role
            preferences[UserPreferences.PASSWORD] = user.password
            preferences[UserPreferences.USER_ID] = user.id
        }
    }

    val user: Flow<UserDto> = dataStore.data.map { preferences ->
        UserDto(
            email = preferences[UserPreferences.EMAIL] ?: "",
            firstName = preferences[UserPreferences.FIRST_NAME] ?: "",
            lastName = preferences[UserPreferences.LAST_NAME] ?: "",
            phoneNumber = preferences[UserPreferences.PHONE_NUMBER] ?: "",
            role = preferences[UserPreferences.ROLE] ?: "",
            password = preferences[UserPreferences.PASSWORD] ?: "",
            id = preferences[UserPreferences.USER_ID] ?: ""
        )
    }
}