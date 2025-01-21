package com.agusteam.travelo.domain.interfaces

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.models.UpcomingOrderTrip

interface OrderRepository {
    suspend fun getUpcomingTrips(userId: String): OperationResult<List<UpcomingOrderTrip>>
    suspend fun getPastTrips(userId: String): OperationResult<List<UpcomingOrderTrip>>
}