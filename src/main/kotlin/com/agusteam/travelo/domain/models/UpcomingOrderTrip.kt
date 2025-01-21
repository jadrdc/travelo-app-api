package com.agusteam.travelo.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class UpcomingOrderTrip(
    val id: String,
    val status: String,
    val customer_id: String,
    val tripScheduleModel: UpcomingTripScheduleOrderModel? = null
)
