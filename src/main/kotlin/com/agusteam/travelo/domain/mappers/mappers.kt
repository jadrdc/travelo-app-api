package com.agusteam.travelo.domain.mappers

import com.agusteam.travelo.calculateMonthsSince
import com.agusteam.travelo.domain.models.BusinessProfileModel
import com.agusteam.travelo.domain.models.TripProfileModel
import com.agusteam.travelo.domain.models.UserProfileDetailsModel
import com.agusteam.travelo.domain.models.UserSignupModel
import kotlin.String


fun UserSignupModel.toProfileDetails(id: String): UserProfileDetailsModel {
    return UserProfileDetailsModel(
        id = id,
        name = name,
        email = email,
        lastname = lastname,
        phone = phone
    )
}

fun BusinessProfileModel.toDomain(): TripProfileModel {
    return TripProfileModel(
        id = id,
        name = name,
        email = email,
        phone = phone,
        address = address,
        rnc = rnc,
        description = description,
        image = image,
        month = calculateMonthsSince(created_at),
        categories = user_business_categories.map { it.category }
    )
}
