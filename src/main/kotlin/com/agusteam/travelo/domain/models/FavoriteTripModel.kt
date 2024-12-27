package com.agusteam.travelo.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class FavoriteTripModel(
    val user_id: String,
    val trip_id: String
)
