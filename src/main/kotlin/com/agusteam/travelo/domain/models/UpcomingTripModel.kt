package com.agusteam.travelo.domain.models

import kotlinx.serialization.Serializable

@Serializable()
data class UpcomingTripModel(
    val id: String,
    val name: String,
    val destiny: String,
    val images: List<String>,
    val businessModel: BusinessProviderTripModel,
    val business_id: String
)
