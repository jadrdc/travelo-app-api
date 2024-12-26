package com.agusteam.travelo.data.impl

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.data.dao.TripsDao
import com.agusteam.travelo.domain.interfaces.TripRepository
import com.agusteam.travelo.domain.models.CreateTripModel
import com.agusteam.travelo.domain.models.PaginatedTripModel
import com.agusteam.travelo.domain.models.TripsModel

class TripRepositoryImp(val dao: TripsDao) : TripRepository {
    override suspend fun createTrip(model: CreateTripModel): OperationResult<Boolean> {
        return try {
            val result = dao.insertTrip(model)
            OperationResult.Success(true)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

    override suspend fun getPaginatedTrips(): OperationResult<List<PaginatedTripModel>> {
        return try {
            val result = dao.getTripsPagination()
            OperationResult.Success(result)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }
}