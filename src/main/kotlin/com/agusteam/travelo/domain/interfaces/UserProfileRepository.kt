package com.agusteam.travelo.domain.interfaces

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.models.UserProfileDetailsModel

interface UserProfileRepository {
    suspend fun getUserProfile(id: String): OperationResult<UserProfileDetailsModel>
}