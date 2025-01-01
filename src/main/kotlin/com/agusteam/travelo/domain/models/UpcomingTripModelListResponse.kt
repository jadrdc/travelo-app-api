package com.agusteam.travelo.domain.models

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable()
data class UpcomingTripModelListResponse(
    val tripModel: UpcomingTripModel? = null,
    val is_active: Boolean,
    val leaving_time: Instant,
    val returning_time: Instant,
    val total_payment: Int
)
