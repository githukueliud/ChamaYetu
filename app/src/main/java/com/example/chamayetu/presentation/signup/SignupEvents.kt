package com.example.chamayetu.presentation.signup

sealed interface SignupEvents {
    data class OnUsernameChanged(val name: String): SignupEvents
    data class OnEmailChanged(val email: String): SignupEvents
    data class OnPasswordChanged(val password: String): SignupEvents

    object OnSignupClicked: SignupEvents
    object OnLoginClicked: SignupEvents
}