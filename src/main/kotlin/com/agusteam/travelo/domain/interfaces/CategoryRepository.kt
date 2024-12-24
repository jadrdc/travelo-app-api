package com.agusteam.travelo.domain.interfaces

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.models.CategoryModel

interface CategoryRepository {
    suspend fun getCategories(): OperationResult<List<CategoryModel>>
}