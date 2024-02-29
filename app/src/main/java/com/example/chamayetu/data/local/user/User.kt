package com.example.chamayetu.data.local.user

data class User(
    var userId: String,
    var email: String,
    var username: String
) {
    constructor(): this("", "", "")
}
