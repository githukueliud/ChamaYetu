package com.example.chamayetu.presentation.login

sealed interface LoginUIEvents {
    data class ShowSnackBar(val message: String): LoginUIEvents
    object NavigateToHome: LoginUIEvents
    object NavigateToSignup: LoginUIEvents
}