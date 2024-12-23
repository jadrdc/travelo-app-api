package com.agusteam.travelo.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class UserSignupModel(
    val name: String,
    val password: String,
    val email: String,
    val phone: String,
    val lastname: String
)
