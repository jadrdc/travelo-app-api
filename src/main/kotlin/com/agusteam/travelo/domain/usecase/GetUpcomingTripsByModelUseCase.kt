package com.agusteam.travelo.domain.usecase

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.interfaces.TripRepository
import com.agusteam.travelo.domain.models.UpcomingTripModelListResponse

class GetUpcomingTripsByModelUseCase(private val repository: TripRepository) {

    suspend operator fun invoke(providerId: String): OperationResult<List<UpcomingTripModelListResponse>> {
        return repository.getUpcomingTrips(providerId)
    }
}