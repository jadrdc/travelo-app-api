package com.agusteam.travelo.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class CategoryModel(
    val id: Int,
    val description: String,
    val image: String? = null,
    val is_active: Boolean
)
