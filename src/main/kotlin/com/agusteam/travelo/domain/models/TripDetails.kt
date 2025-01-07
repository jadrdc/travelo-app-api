package com.agusteam.travelo.domain.models

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class TripDetails(
    val leaving_time: Instant = Clock.System.now(),
    val returning_time: Instant = Clock.System.now(),
    val meeting_point: String = "",
    val initial_payment: Int = 0,
    val total_payment: Int = 0,
    val is_active: Boolean = false
)