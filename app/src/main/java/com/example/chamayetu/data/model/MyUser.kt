package com.example.chamayetu.data.model

data class MyUser(
    val firstname: String,
    val lastname: String,
    val email: String,
    val password: String,
    val phoneNumber: String,
    val role: String = "USER"
)
