package com.agusteam.travelo.domain.usecase

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.interfaces.UserProfileRepository
import com.agusteam.travelo.domain.models.UserProfileDetailsModel

class GetProfileDetailsUseCase(private val repository: UserProfileRepository) {
    suspend operator fun invoke(userId: String): OperationResult<UserProfileDetailsModel> {
        return repository.getUserProfile(userId)
    }
}