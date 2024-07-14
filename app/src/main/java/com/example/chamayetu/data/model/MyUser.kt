package com.example.chamayetu.data.model

data class MyUser(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String,
    val password: String,
    val role: String = "USER"
)
