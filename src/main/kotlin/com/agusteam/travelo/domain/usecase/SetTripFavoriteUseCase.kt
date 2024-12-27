package com.agusteam.travelo.domain.usecase

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.interfaces.TripRepository
import com.agusteam.travelo.domain.models.CreateTripModel
import com.agusteam.travelo.domain.models.FavoriteTripModel

class SetTripFavoriteUseCase(val repository: TripRepository) {
    suspend operator fun invoke(model: FavoriteTripModel): OperationResult<Boolean> {
        return repository.setFavoriteTrip(model)
    }
}