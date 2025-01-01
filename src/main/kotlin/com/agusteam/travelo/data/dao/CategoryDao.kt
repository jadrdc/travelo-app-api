package com.agusteam.travelo.data.dao

import com.agusteam.travelo.domain.models.CategoryModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns

class CategoryDao(supabase: SupabaseClient) {
    val db = supabase.postgrest

    suspend fun getCategories(): List<CategoryModel> {
        val categories =
            db.from("categories").select(columns = Columns.list("id", "description", "image", "is_active")) {
                filter {
                    CategoryModel::is_active
                }
            }.decodeList<CategoryModel>()

        return categories
    }

    suspend fun getCategoriesByProvider(providerId: String): List<CategoryModel> {
        val categories =
            db.from("categories").select(columns = Columns.list("id", "description", "image", "is_active")) {
                filter {
                    CategoryModel::is_active
                }
            }.decodeList<CategoryModel>()

        return categories
    }
}