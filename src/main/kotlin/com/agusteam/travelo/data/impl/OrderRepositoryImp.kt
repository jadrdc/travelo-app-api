package com.agusteam.travelo.data.impl

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.data.dao.OrdersDao
import com.agusteam.travelo.domain.interfaces.OrderRepository
import com.agusteam.travelo.domain.models.UpcomingOrderTrip

class OrderRepositoryImp(private val ordersDao: OrdersDao) : OrderRepository {
    override suspend fun getUpcomingTrips(userId: String): OperationResult<List<UpcomingOrderTrip>> {
        return try {
            val result = ordersDao.getUpcomingTrips(userId)
            OperationResult.Success(result)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

    override suspend fun getPastTrips(userId: String): OperationResult<List<UpcomingOrderTrip>> {
        return try {
            val result = ordersDao.getPastTrips(userId)
            OperationResult.Success(result)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }
}