package com.agusteam.travelo.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class TripProfileModel(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val address: String,
    val rnc: String,
    val description: String,
    val image: String,
    val month: Int,
    val categories: List<CategoryModel> = listOf<CategoryModel>(),
    val tripOffers: Int
)