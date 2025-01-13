package com.agusteam.travelo.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class TripsID( val id: String,
                    val name: String,
                    val description: String,
                    val destiny: String,
                    val lat: Float,
                    val lng: Float,)
