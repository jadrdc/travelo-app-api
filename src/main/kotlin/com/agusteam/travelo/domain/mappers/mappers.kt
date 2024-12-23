package com.agusteam.travelo.domain.mappers

import com.agusteam.travelo.domain.models.UserProfileDetailsModel
import com.agusteam.travelo.domain.models.UserSignupModel


fun UserSignupModel.toProfileDetails(id: String): UserProfileDetailsModel {
    return UserProfileDetailsModel(
        id = id,
        name = name,
        email = email,
        lastname = lastname,
        phone = phone
    )
}

