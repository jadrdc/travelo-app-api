package com.agusteam.travelo.data.impl

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.data.dao.UserProfileDao
import com.agusteam.travelo.domain.interfaces.UserProfileRepository
import com.agusteam.travelo.domain.models.UserProfileDetailsModel

class UserProfileRepositoryImp(val dao: UserProfileDao) : UserProfileRepository {
    override suspend fun getUserProfile(id: String): OperationResult<UserProfileDetailsModel> {
        return try {
            val result = dao.getUserProfile(id)
            if (result == null) {
                OperationResult.Error(Exception("Perfil no encontrado"))
            } else {
                OperationResult.Success(result)
            }

        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }
}