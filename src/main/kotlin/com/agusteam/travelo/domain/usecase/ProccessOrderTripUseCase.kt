package com.agusteam.travelo.domain.usecase

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.interfaces.PaymentRepository
import com.agusteam.travelo.domain.models.OrderModel

class ProccessOrderTripUseCase(val repository: PaymentRepository) {
    suspend operator fun invoke(orderModel: OrderModel): OperationResult<String> {
        return repository.processOrder(orderModel)
    }
}