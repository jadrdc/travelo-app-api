package com.agusteam.travelo.domain.usecase

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.interfaces.TripRepository
import com.agusteam.travelo.domain.models.CreateTripModel

class CreateTripUseCase(val repository: TripRepository) {
    suspend operator fun invoke(model: CreateTripModel): OperationResult<Boolean> {
        return repository.createTrip(model)
    }
}