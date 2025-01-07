package com.agusteam.travelo.domain.usecase

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.interfaces.TripRepository
import com.agusteam.travelo.domain.models.PaginatedTripCategoryModel
import com.agusteam.travelo.domain.models.TripAvailablePaginationRequestModel

class GetPaginatedTripDetailUseCase(val repository: TripRepository) {
    suspend operator fun invoke(requestModel: TripAvailablePaginationRequestModel): OperationResult<List<PaginatedTripCategoryModel>> {
        return repository.getActiveTrips(
            requestModel
        )
    }
}