package com.agusteam.travelo.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class OrderModel(val customer_id: String, val trip_schedule_id: String)
