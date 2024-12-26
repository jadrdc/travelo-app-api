package com.agusteam.travelo.domain.usecase

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.interfaces.UserProfileRepository
import com.agusteam.travelo.domain.models.BusinessProfileModel

class GetBusinessProfileUseCase(
    private val repository: UserProfileRepository
) {
    suspend operator fun invoke(id: String): OperationResult<List<BusinessProfileModel>> {
        return try {
                 repository.getBusinessProfile(id)
        } catch (e: Exception) {
            OperationResult.Error(Exception(e.message))
        }

    }
}