package com.agusteam.travelo.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class ProcessPendingOrderRequestModel(val customer: String, val tripDetail: String)

fun ProcessPendingOrderRequestModel.toOrder(): OrderModel {
    return OrderModel(customer_id = customer, trip_schedule_id = tripDetail)
}