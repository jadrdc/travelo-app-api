package com.agusteam.travelo.domain.models

import com.agusteam.travelo.calculateMonthsSince
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class TripModel(
    val id: String, val name: String,
    val image: String,
    val created_at: Instant,
    val month: Int = calculateMonthsSince(created_at)
)