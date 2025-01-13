package com.agusteam.travelo.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class TripScheduleQuorumModel(
    val id: String,
    val available: Int,
    val quorum: Int
)
