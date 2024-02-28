package com.example.chamayetu.presentation.login

data class LoginState (
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val loading: Boolean = false,
)