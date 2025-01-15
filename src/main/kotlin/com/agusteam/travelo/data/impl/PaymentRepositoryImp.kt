package com.agusteam.travelo.data.impl

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.data.dao.PaymentDao
import com.agusteam.travelo.domain.interfaces.PaymentRepository
import com.agusteam.travelo.domain.models.OrderFailureModel
import com.agusteam.travelo.domain.models.OrderModel

class PaymentRepositoryImp(val dao: PaymentDao) : PaymentRepository {
    override suspend fun processOrder(orderModel: OrderModel): OperationResult<String> {
        return try {
            val result = dao.processOrder(orderModel)
            OperationResult.Success(result)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

    override suspend fun processOrderSucess(order: String,transactionId:String): OperationResult<Boolean> {
        return try {
            val result = dao.processOrderSucess(order,transactionId)
            OperationResult.Success(result)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }

    }

    override suspend fun processOrderFailure(orderModel: OrderFailureModel): OperationResult<Boolean> {
        return try {
            val result = dao.processOrderFailure(orderModel)
            OperationResult.Success(result)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }
}