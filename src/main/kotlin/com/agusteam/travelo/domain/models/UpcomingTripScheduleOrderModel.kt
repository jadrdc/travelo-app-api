package com.agusteam.travelo.domain.models

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class UpcomingTripScheduleOrderModel(
    val is_active: Boolean,
    val total_payment: Int,
    val returning_time: Instant,
    val leaving_time: String,
    val tripModel: UpcomingTripOrderModel
)
