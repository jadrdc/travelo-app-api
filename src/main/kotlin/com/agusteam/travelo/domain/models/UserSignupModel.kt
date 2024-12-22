package com.agusteam.travelo.domain.models

data class UserSignupModel(
    val name: String,
    val password: String,
    val email: String,
    val phone: String,
    val lastname: String
) {
    fun validate(): Boolean {
        return name.isNotBlank() && password.isNotBlank() && email.isNotBlank() &&
                phone.isNotBlank() && lastname.isNotBlank()
    }
}
