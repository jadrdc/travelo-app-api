package com.agusteam.travelo.data.impl

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.interfaces.UserSignUpRepository
import com.agusteam.travelo.domain.mappers.toProfileDetails
import com.agusteam.travelo.domain.models.LoginModel
import com.agusteam.travelo.domain.models.LogonUserModel
import com.agusteam.travelo.domain.models.RequestPasswordChangeModel
import com.agusteam.travelo.domain.models.UserSignupModel
import com.agusteam.travelo.getAdminSupaBase
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest

class UserSignUpRepositoryImp(supabase: SupabaseClient) : UserSignUpRepository {
    val auth = supabase.auth
    val db = supabase.postgrest

    override suspend fun resetPasswordForEmail(model: RequestPasswordChangeModel): OperationResult<Boolean> {
        return try {
            auth.resetPasswordForEmail(email = model.email)
            OperationResult.Success(true)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

    override suspend fun login(model: LoginModel): OperationResult<LogonUserModel> {
        return try {
            auth.signInWith(Email) {
                email = model.email
                password = model.password
            }
            val currentUser = auth.currentUserOrNull()
            if (currentUser != null) {
                OperationResult.Success(LogonUserModel(id = currentUser.id, email = currentUser.email ?: " "))
            } else {
                OperationResult.Error(Exception("User not found"))

            }
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

    override suspend fun signUpUser(model: UserSignupModel): OperationResult<Boolean> {
        val userResponse = try {

            auth.signUpWith(Email) {
                email = model.email
                password = model.password
            }
        } catch (e: Exception) {
            return OperationResult.Error(e)
        }
        if (userResponse == null) {
            return OperationResult.Success(false)
        }
        return OperationResult.Success(true)

        return try {
            val userDetails = model.toProfileDetails(userResponse.id)
            db.from("user_profile").insert(userDetails)
            OperationResult.Success(true)
        } catch (error: Exception) {
            rollbackUserSignup(userResponse.id)
            OperationResult.Error(error)
        }
    }


    private suspend fun rollbackUserSignup(userId: String) {
        try {
            val supabaseClient = getAdminSupaBase()
            val auth = supabaseClient.auth
            auth.admin.deleteUser(userId)
            println("Rolled back user signup for ID: $userId")
        } catch (rollbackError: Exception) {
            println("Error during rollback for user ID $userId: ${rollbackError.message}")
        }
    }
}