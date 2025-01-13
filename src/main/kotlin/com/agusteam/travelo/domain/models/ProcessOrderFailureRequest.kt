package com.agusteam.travelo.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class ProcessOrderFailureRequest(val order: String, val tripscheduled: String, val reason: String) {

    fun toDomain(): OrderFailureModel {
        return OrderFailureModel(order_id = order, tripscheduled = tripscheduled, reason = reason)
    }
}
