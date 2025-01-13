package com.agusteam.travelo.domain.interfaces

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.models.OrderModel

interface PaymentRepository {
    suspend fun processOrder(orderModel: OrderModel): OperationResult<Boolean>
}