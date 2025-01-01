package com.agusteam.travelo.domain.models

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class TripScheduleModel(
    val tripModel: TripsModel,
  //  val businessModel: BusinessProviderTripModel,
     val leaving_time: Instant,
    val returning_time: Instant,
    val meeting_point: String,
    val initial_payment: Int,
    val total_payment: Int
)
