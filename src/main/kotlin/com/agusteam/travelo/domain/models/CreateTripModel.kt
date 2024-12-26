package com.agusteam.travelo.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class CreateTripModel(
    val name: String,
    val description: String,
    val destiny: String,
    val lat: Float,
    val lng: Float,
    val business_id: String
)
