package com.agusteam.travelo.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class UpcomingTripOrderModel(
    val name: String,
    val destiny: String,
    val images: List<String>,
    val businessModel: UpcomingTripBusinessModel
)
