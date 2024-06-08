package com.example.chamayetu.presentation.signup

data class SignupState(
    val username: String = "",
    val usernameError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val loading: Boolean = false,
    val phoneNumber: String = "",
    val phoneNumberError: String? = null,
    val firstName: String = "",
    val lastName: String = "",
    val firstNameError: String? = null,
    val lastNameError: String? = null
)
