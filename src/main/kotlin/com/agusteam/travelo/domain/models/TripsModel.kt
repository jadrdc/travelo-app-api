package com.agusteam.travelo.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class TripsModel(
    val id: String,
    val name: String,
    val description: String,
    val destiny: String,
    val lat: Float,
    val lng: Float,
    val images: List<String>,
    val cancellation_policy: String,
)
