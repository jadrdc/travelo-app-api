package com.agusteam.travelo.domain.interfaces

import com.agusteam.travelo.domain.models.UserSignupModel

interface UserSignUpRepository {
    suspend fun signUpUser(model: UserSignupModel)
}