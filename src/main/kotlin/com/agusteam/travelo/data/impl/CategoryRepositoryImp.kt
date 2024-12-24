package com.agusteam.travelo.data.impl

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.data.dao.CategoryDao
import com.agusteam.travelo.domain.interfaces.CategoryRepository
import com.agusteam.travelo.domain.models.CategoryModel

class CategoryRepositoryImp(val dao: CategoryDao) : CategoryRepository {
    override suspend fun getCategories(): OperationResult<List<CategoryModel>> {
        return try {
            val categories = dao.getCategories()
            OperationResult.Success(categories)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }
}