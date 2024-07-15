package com.example.chamayetu.data.network

data class UserDto(
    val email: String,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val password: String,
    val phoneNumber: String,
    val role: String
)