package com.agusteam.travelo.domain.interfaces

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.models.*

interface TripRepository {
    suspend fun setFavoriteTrip(model: FavoriteTripModel): OperationResult<Boolean>
    suspend fun createTrip(model: CreateTripModel): OperationResult<Boolean>
    suspend fun getPaginatedTrips(): OperationResult<List<PaginatedTripModel>>
    suspend fun removeFavorite(model: FavoriteTripModel): OperationResult<Boolean>
    suspend fun getTripsIncludedServices(tripId: String): OperationResult<List<String>>
    suspend fun getActiveTrips(req: TripAvailablePaginationRequestModel): OperationResult<List<PaginatedTripCategoryModel>>
    suspend fun getUpcomingTrips(providerId: String): OperationResult<List<UpcomingTripModelListResponse>>
    suspend fun getFavoriteTripList(userId: String): OperationResult<List<PaginatedFavoriteTripModel>>
    suspend fun isFavoriteTrip(userId: String, tripId: String): OperationResult<Boolean>
}