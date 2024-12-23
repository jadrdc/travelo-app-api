package com.agusteam.travelo.data.validations

import com.agusteam.travelo.domain.models.UserSignupModel


class FieldValidator {

    // Regex for a valid email
    private val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$")

    // Regex for a valid phone number (basic validation for international format)
    private val phoneRegex = Regex("^\\+?[1-9][0-9]{7,14}\$")

    // Password should be at least 8 characters, with a mix of uppercase, lowercase, digits, and special characters
    private val passwordRegex = Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}\$")

    // Validate email format
    fun validateEmail(email: String): Boolean {
        return email.matches(emailRegex)
    }

    // Validate phone number format
    fun validatePhone(phone: String): Boolean {
        return phone.matches(phoneRegex)
    }

    // Validate password security
    fun validatePassword(password: String): Boolean {
        return password.matches(passwordRegex)
    }

    // Method to validate all fields at once
    fun validate(user: UserSignupModel): List<String> {
        val errors = mutableListOf<String>()

        if (!validateEmail(user.email)) {
            errors.add("Invalid email format")
        }
        if (!validatePhone(user.phone)) {
            errors.add("Invalid phone number format")
        }
        if (!validatePassword(user.password)) {
            errors.add("Password must be at least 8 characters long, contain uppercase, lowercase, a digit, and a special character.")
        }
        if (user.name.isBlank()) {
            errors.add("Name cannot be blank")
        }
        if (user.lastname.isBlank()) {
            errors.add("Lastname cannot be blank")
        }

        return errors
    }
}