package com.agusteam.travelo.data.impl

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.data.dao.UserProfileDao
import com.agusteam.travelo.domain.interfaces.UserProfileRepository
import com.agusteam.travelo.domain.models.BusinessProfileModel
import com.agusteam.travelo.domain.models.CreateBusinessProfileModel
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

    override suspend fun createBusinessProfile(model: CreateBusinessProfileModel): OperationResult<CreateBusinessProfileModel> {
        return try {
            val result = dao.insertBusinessProfile(model)
            OperationResult.Success(model)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

    override suspend fun getBusinessProfile(id: String): OperationResult<List<BusinessProfileModel>> {
        return try {
            val result = dao.getBusinessProfile(id)
            if (result == null) {
                OperationResult.Error(Exception("Perfil no encontrado"))
            } else {
                OperationResult.Success(result)
            }

        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

    override suspend fun getTripsOfferCount(id: String): OperationResult<Int> {
        return try {
            val result = dao.getOfferTripsByProvider(id)
            OperationResult.Success(result)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

    override suspend fun getTotalActiveTrips(id: String): OperationResult<Int> {
        return try {
            val result = dao.getTotalActiveTrips(id)
            OperationResult.Success(result)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }    }
}