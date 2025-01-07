package com.agusteam.travelo.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class TripAvailablePaginationRequestModel(
    val category: String,
    val startingAmount: Int = 0,
    val endingAmount: Int = 0
)
