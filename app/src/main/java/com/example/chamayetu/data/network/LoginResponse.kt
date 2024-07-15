package com.example.chamayetu.data.network

data class LoginResponse(
    val token: String,
    val userDto: UserDto
)