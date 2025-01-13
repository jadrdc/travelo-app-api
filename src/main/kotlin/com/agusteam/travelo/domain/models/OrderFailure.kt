package com.agusteam.travelo.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class OrderFailureModel(
    val order_id: String, val reason: String, val tripscheduled: String
)
