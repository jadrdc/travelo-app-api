package com.agusteam.travelo.domain.usecase

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.interfaces.OrderRepository
import com.agusteam.travelo.domain.models.UpcomingOrderTrip

class GetUpcomingOrderTrip(private val orderRepository: OrderRepository) {
    suspend fun getOrdersUpcomingTrip(userId: String): OperationResult<List<UpcomingOrderTrip>> {
        return orderRepository.getUpcomingTrips(userId)
    }

    suspend fun getPastTrips(userId: String): OperationResult<List<UpcomingOrderTrip>> {
        return orderRepository.getPastTrips(userId)
    }
}