package com.agusteam.travelo.domain.models

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
    val creation_date: String
)