package com.example.chamayetu.data.network

data class SignupResponse(
    val email: String,
    val firstname: String,
    val id: Int,
    val lastname: String,
    val password: String,
    val phoneNumber: String,
    val role: String
)