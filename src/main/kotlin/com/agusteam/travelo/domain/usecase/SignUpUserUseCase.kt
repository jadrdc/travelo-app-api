package com.agusteam.travelo.domain.usecase

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.data.validations.FieldValidator
import com.agusteam.travelo.domain.interfaces.UserSignUpRepository
import com.agusteam.travelo.domain.models.LoginModel
import com.agusteam.travelo.domain.models.LogonUserModel
import com.agusteam.travelo.domain.models.RequestPasswordChangeModel
import com.agusteam.travelo.domain.models.UserSignupModel

class SignUpUserUseCase(
    val repository: UserSignUpRepository,
    val validator: FieldValidator
) {

    suspend fun login(model: LoginModel): OperationResult<LogonUserModel> {
        return repository.login(model)
    }

    suspend fun resetPasswordForEmail(model: RequestPasswordChangeModel): OperationResult<Boolean> {
        return repository.resetPasswordForEmail(model)
    }

    suspend fun signUpUser(userSignUpModel: UserSignupModel): OperationResult<Boolean> {
        return try {
            val errors = validator.validate(userSignUpModel)
            if (errors.isNotEmpty()) {
                OperationResult.Error(Exception(errors.joinToString(", ")))
            } else {
                repository.signUpUser(userSignUpModel)
            }
        } catch (e: Exception) {
            OperationResult.Error(Exception(e.message))
        }
    }
}