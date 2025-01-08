package com.agusteam.travelo.domain.interfaces

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.models.BusinessProfileModel
import com.agusteam.travelo.domain.models.CreateBusinessProfileModel
import com.agusteam.travelo.domain.models.UserProfileDetailsModel

interface UserProfileRepository {
    suspend fun getUserProfile(id: String): OperationResult<UserProfileDetailsModel>
    suspend fun getTripsOfferCount(id: String): OperationResult<Int>
    suspend fun getTotalActiveTrips(id: String): OperationResult<Int>
    suspend fun getBusinessProfile(id: String): OperationResult<List<BusinessProfileModel>>
    suspend fun createBusinessProfile(model: CreateBusinessProfileModel): OperationResult<CreateBusinessProfileModel>
}