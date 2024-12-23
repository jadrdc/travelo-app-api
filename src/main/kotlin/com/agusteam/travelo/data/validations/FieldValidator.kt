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
            errors.add("Formato de correo electrónico inválido")
        }
        if (!validatePhone(user.phone)) {
            errors.add("Formato de número de teléfono inválido")
        }
        if (!validatePassword(user.password)) {
            errors.add("La contraseña debe tener al menos 8 caracteres, incluir mayúsculas, minúsculas, un dígito y un carácter especial.")
        }
        if (user.name.isBlank()) {
            errors.add("El nombre no puede estar vacío")
        }
        if (user.lastname.isBlank()) {
            errors.add("El apellido no puede estar vacío")
        }


        return errors
    }
}