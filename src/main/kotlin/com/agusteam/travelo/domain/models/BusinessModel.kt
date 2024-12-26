package com.agusteam.travelo.domain.models

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class BusinessModel(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val address: String,
    val rnc: String,
    val description: String,
    val image: String,
    val created_at: Instant
)
