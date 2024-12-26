package com.agusteam.travelo.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class BusinessProfileModel(
    val user_business_id: String,
    val category_id: String,
    val business: BusinessModel,
    val category: CategoryModel

)