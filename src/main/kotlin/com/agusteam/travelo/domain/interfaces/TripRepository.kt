package com.agusteam.travelo.domain.interfaces

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.models.CreateTripModel
import com.agusteam.travelo.domain.models.FavoriteTripModel
import com.agusteam.travelo.domain.models.PaginatedTripModel
import com.agusteam.travelo.domain.models.TripScheduleModel

interface TripRepository {
    suspend fun setFavoriteTrip(model: FavoriteTripModel): OperationResult<Boolean>
    suspend fun createTrip(model: CreateTripModel): OperationResult<Boolean>
    suspend fun getPaginatedTrips(): OperationResult<List<PaginatedTripModel>>
    suspend fun removeFavorite(model: FavoriteTripModel): OperationResult<Boolean>
    suspend fun getTripsIncludedServices(tripId: String): OperationResult<List<String>>
    suspend fun getActiveTrips(): OperationResult<List<TripScheduleModel>>
}