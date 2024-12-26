package com.agusteam.travelo.domain.interfaces

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.models.CreateTripModel
import com.agusteam.travelo.domain.models.PaginatedTripModel
import com.agusteam.travelo.domain.models.TripsModel

interface TripRepository {
    suspend fun createTrip(model: CreateTripModel): OperationResult<Boolean>
    suspend fun getPaginatedTrips(): OperationResult<List<PaginatedTripModel>>
}