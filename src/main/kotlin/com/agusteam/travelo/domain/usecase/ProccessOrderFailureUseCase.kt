package com.agusteam.travelo.domain.usecase

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.interfaces.PaymentRepository
import com.agusteam.travelo.domain.models.OrderFailureModel

class ProccessOrderFailureUseCase(val repository: PaymentRepository) {
    suspend operator fun invoke(orderModel: OrderFailureModel): OperationResult<Boolean> {
        return repository.processOrderFailure(orderModel)
    }
}