package com.agusteam.travelo.domain.usecase

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.interfaces.PaymentRepository

class ProccessOrderSucessUseCase(val repository: PaymentRepository) {
    suspend operator fun invoke(order: String): OperationResult<Boolean> {
        return repository.processOrderSucess(order)
    }
}