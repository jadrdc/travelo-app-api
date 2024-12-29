package com.agusteam.travelo.domain.usecase

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.interfaces.TripRepository

class GetTripIncludedServicesUseCase(val repository: TripRepository) {

    suspend  operator fun invoke(trip: String): OperationResult<List<String>> {
        return repository.getTripsIncludedServices(trip)
    }
}