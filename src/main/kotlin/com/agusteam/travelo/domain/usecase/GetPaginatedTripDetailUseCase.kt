package com.agusteam.travelo.domain.usecase

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.interfaces.TripRepository
import com.agusteam.travelo.domain.models.TripScheduleModel

class GetPaginatedTripDetailUseCase(val repository: TripRepository) {
    suspend operator fun invoke(): OperationResult<List<TripScheduleModel>> {
        return repository.getActiveTrips()
    }
}