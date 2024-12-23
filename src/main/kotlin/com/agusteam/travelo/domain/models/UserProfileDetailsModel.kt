package com.agusteam.travelo.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class UserProfileDetailsModel(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val lastname: String
)
