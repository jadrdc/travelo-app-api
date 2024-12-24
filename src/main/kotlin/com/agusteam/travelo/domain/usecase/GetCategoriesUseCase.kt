package com.agusteam.travelo.domain.usecase

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.data.validations.FieldValidator
import com.agusteam.travelo.domain.interfaces.CategoryRepository
import com.agusteam.travelo.domain.interfaces.UserProfileRepository
import com.agusteam.travelo.domain.models.CategoryModel
import com.agusteam.travelo.domain.models.CreateBusinessProfileModel

class GetCategoriesUseCase(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(): OperationResult<List<CategoryModel>> {
        return try {
            repository.getCategories()
        } catch (e: Exception) {
            OperationResult.Error(Exception(e.message))
        }

    }
}