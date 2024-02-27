package com.example.chamayetu.data.repository

import android.util.Patterns
import com.example.chamayetu.utils.ValidationResult

class FormValidationRepository {

    fun validateEmail(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                message = "Email cannot be blank"
            )
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                message = "Please enter a valid email"
            )
        }
        return ValidationResult(
            successful = true
        )
    }

    fun validateUsername(username: String): ValidationResult{
        if (username.isBlank()) {
            return ValidationResult(
                successful = false,
                message = "Please enter your name"
            )
        }
        return ValidationResult(
            successful = true
        )
    }

    fun validatePassword(password: String): ValidationResult {
        if (password.length < 8) {
            return ValidationResult(
                successful = false,
                message = "Password needs to be at least 8 characters"
            )
        }
        val containsLettersAndDigits = password.any{it.isDigit()} &&
                password.any { it.isLetter() }

        if (!containsLettersAndDigits) {
            return ValidationResult(
                successful = false,
                message = "The password must contain both letters and numbers"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}