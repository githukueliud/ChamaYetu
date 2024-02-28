package com.example.chamayetu.presentation.login

sealed interface LoginEvents {
    object OnLoginClicked: LoginEvents
    object OnSignupClicked: LoginEvents
    data class OnPasswordChanged(val password: String): LoginEvents
    data class OnEmailChanged(val email: String): LoginEvents
}