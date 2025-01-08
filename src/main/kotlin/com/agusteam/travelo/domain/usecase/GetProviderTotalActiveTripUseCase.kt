package com.agusteam.travelo.domain.usecase

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.interfaces.UserProfileRepository

class GetProviderTotalActiveTripUseCase(val repository: UserProfileRepository) {
    suspend operator fun invoke(provider:String): OperationResult<Int> {
        return repository.getTotalActiveTrips(provider)
    }
}