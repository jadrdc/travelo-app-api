package com.agusteam.travelo.data.impl

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.data.dao.TripsDao
import com.agusteam.travelo.domain.interfaces.TripRepository
import com.agusteam.travelo.domain.models.*
import com.agusteam.travelo.handleApiException
import io.github.jan.supabase.exceptions.UnknownRestException

class TripRepositoryImp(val dao: TripsDao) : TripRepository {
    override suspend fun createTrip(model: CreateTripModel): OperationResult<Boolean> {
        return try {
            val result = dao.insertTrip(model)
            OperationResult.Success(true)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

    override suspend fun getUpcomingTrips(providerId: String): OperationResult<List<UpcomingTripModelListResponse>> {
        return try {
            val result = dao.getUpcomingTrips(providerId)
            OperationResult.Success(result)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

    override suspend fun getFavoriteTripList(userId: String): OperationResult<List<PaginatedFavoriteTripModel>> {
        return try {
            val result = dao.getFavoriteTripList(userId)
            OperationResult.Success(result)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

    override suspend fun getActiveTrips(req: TripAvailablePaginationRequestModel): OperationResult<List<PaginatedTripCategoryModel>> {
        return try {
            val result = dao.getActiveTrips(req)
            OperationResult.Success(result)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

    override suspend fun getTripsIncludedServices(tripId: String): OperationResult<List<String>> {
        return try {
            val result = dao.getTripIncluded(tripId).map { it.description }
            OperationResult.Success(result)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

    override suspend fun setFavoriteTrip(model: FavoriteTripModel): OperationResult<Boolean> {
        return try {
            val result = dao.setFavoriteTrip(model)
            OperationResult.Success(true)
        } catch (e: UnknownRestException) {
            OperationResult.Error(
                Exception(
                    handleApiException(e)
                )
            )
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

    override suspend fun removeFavorite(model: FavoriteTripModel): OperationResult<Boolean> {
        return try {
            val result = dao.removeFavorite(model)
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