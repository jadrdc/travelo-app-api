package com.agusteam.travelo.data.impl

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.data.dao.PaymentDao
import com.agusteam.travelo.domain.interfaces.PaymentRepository
import com.agusteam.travelo.domain.models.OrderModel

class PaymentRepositoryImp(val dao: PaymentDao) : PaymentRepository {
    override suspend fun processOrder(orderModel: OrderModel): OperationResult<Boolean> {
        return try {
            val result = dao.processOrder(orderModel)
            OperationResult.Success(true)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }
}