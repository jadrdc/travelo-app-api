package com.agusteam.travelo.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class RequestPasswordChangeModel(val email: String)
