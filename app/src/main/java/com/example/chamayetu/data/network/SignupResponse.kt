package com.example.chamayetu.data.network

data class SignupResponse(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String,
    val password: String,
    val role: String
)