package com.agusteam.travelo.data.dao

import com.agusteam.travelo.domain.models.UserProfileDetailsModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns

class UserProfileDao(supabase: SupabaseClient) {
    val db = supabase.postgrest

    suspend fun userExists(email: String): Boolean {
        val result = db.from("user_profile").select {
            filter {
                UserProfileDetailsModel::email eq email
            }
        }.decodeList<UserProfileDetailsModel>()

        return result.isNotEmpty()
    }

    suspend fun insertUserProfile(userDetails: UserProfileDetailsModel) {
        db.from("user_profile").insert(userDetails)
    }

    suspend fun getUserProfile(id: String): UserProfileDetailsModel? {
        return db.from("user_profile").select(columns = Columns.list("id", "name", "email", "phone", "lastname")) {
            filter {
                UserProfileDetailsModel::id eq id
            }
        }.decodeSingleOrNull<UserProfileDetailsModel>()
    }
}