package com.agusteam.travelo.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class TripAvailablePaginationRequestModel(
    val category: String,
    val endingAmount: Int = 0,
    val search: String? = null,
)
