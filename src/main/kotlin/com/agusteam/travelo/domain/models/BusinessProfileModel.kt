package com.agusteam.travelo.domain.models

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class BusinessProfileModel(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val address: String,
    val rnc: String,
    val description: String,
    val image: String,
    val created_at:Instant,
    val user_business_categories: List<BusinessCategory> = listOf<BusinessCategory>()

)