package com.agusteam.travelo.domain.usecase

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.data.validations.FieldValidator
import com.agusteam.travelo.domain.interfaces.UserProfileRepository
import com.agusteam.travelo.domain.models.CreateBusinessProfileModel

class CreteBusinessProfileUseCase(
    val validator: FieldValidator,
    private val repository: UserProfileRepository
) {
    suspend operator fun invoke(model: CreateBusinessProfileModel): OperationResult<CreateBusinessProfileModel> {

        return try {
            val errors = validator.validateBusinessProfileModel(model)
            if (errors.isNotEmpty()) {
                OperationResult.Error(Exception(errors.joinToString(", ")))
            } else {
                return repository.createBusinessProfile(model)
            }
        } catch (e: Exception) {
            OperationResult.Error(Exception(e.message))
        }

    }
}