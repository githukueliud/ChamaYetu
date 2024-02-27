package com.example.chamayetu.presentation.signup

sealed interface SignupUiEvents {
    object NavigateToLogin: SignupUiEvents
    data class ShowSnackBar(val message: String): SignupUiEvents
}