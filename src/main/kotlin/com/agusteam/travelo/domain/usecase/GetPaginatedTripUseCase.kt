package com.agusteam.travelo.domain.usecase

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.interfaces.TripRepository
import com.agusteam.travelo.domain.models.PaginatedTripModel

class GetPaginatedTripUseCase(val repository: TripRepository) {
    suspend operator fun invoke(): OperationResult<List<PaginatedTripModel>> {
        return repository.getPaginatedTrips()
    }
}