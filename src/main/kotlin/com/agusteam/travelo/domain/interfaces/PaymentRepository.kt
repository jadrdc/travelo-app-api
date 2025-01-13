package com.agusteam.travelo.domain.interfaces

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.models.OrderFailureModel
import com.agusteam.travelo.domain.models.OrderModel

interface PaymentRepository {
    suspend fun processOrder(orderModel: OrderModel): OperationResult<String>
    suspend fun processOrderFailure(orderModel: OrderFailureModel): OperationResult<Boolean>
    suspend fun processOrderSucess(order:String): OperationResult<Boolean>
}