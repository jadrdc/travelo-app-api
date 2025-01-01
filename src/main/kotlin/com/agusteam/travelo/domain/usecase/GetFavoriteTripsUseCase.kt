package com.agusteam.travelo.domain.usecase

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.interfaces.TripRepository
import com.agusteam.travelo.domain.models.PaginatedFavoriteTripModel
import com.agusteam.travelo.domain.models.PaginatedTripModel

class GetFavoriteTripsUseCase(private val repository: TripRepository) {

    suspend operator fun invoke(providerId: String): OperationResult<List<PaginatedFavoriteTripModel>> {
        return repository.getFavoriteTripList(providerId)
    }
}