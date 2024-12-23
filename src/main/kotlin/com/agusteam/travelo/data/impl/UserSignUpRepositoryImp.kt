package com.agusteam.travelo.data.impl

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.data.dao.UserProfileDao
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

class UserSignUpRepositoryImp(
    supabase: SupabaseClient,
    val userProfileDao: UserProfileDao
) : UserSignUpRepository {
    val auth = supabase.auth

    override suspend fun comfirmEmail(userId: String): OperationResult<Boolean> {
        return try {
            val response = auth.admin.updateUserById(uid = userId) {
                emailConfirm = true
            }
            OperationResult.Success(true)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }

    }

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
                OperationResult.Error(Exception("Usuario no encontrado"))

            }
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

    override suspend fun signUpUser(model: UserSignupModel): OperationResult<Boolean> {
        try {
            val exists = userProfileDao.userExists(model.email)
            if (exists) {
                return OperationResult.Error(Exception("Usuario ya existe"))
            } else {
                return createUser(model)
            }
        } catch (e: Exception) {
            return OperationResult.Error(e)
        }

    }

    private suspend fun createUser(model: UserSignupModel): OperationResult<Boolean> {
        val userResponse = auth.signUpWith(Email) {
            email = model.email
            password = model.password
        }
        if (userResponse == null) {
            return OperationResult.Error(Exception("Usuario no puede ser creado"))
        } else {
            return try {
                userProfileDao.insertUserProfile(model.toProfileDetails(userResponse.id))
                OperationResult.Success(true)
            } catch (error: Exception) {
                rollbackUserSignup(userResponse.id)
                OperationResult.Error(error)
            }
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