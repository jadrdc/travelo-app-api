package com.agusteam.travelo.domain.interfaces

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.models.LoginModel
import com.agusteam.travelo.domain.models.LogonUserModel
import com.agusteam.travelo.domain.models.RequestPasswordChangeModel
import com.agusteam.travelo.domain.models.UserSignupModel

interface UserSignUpRepository {
    suspend fun signUpUser(model: UserSignupModel): OperationResult<Boolean>
    suspend fun login(model: LoginModel): OperationResult<LogonUserModel>
    suspend fun resetPasswordForEmail(model: RequestPasswordChangeModel): OperationResult<Boolean>
}