package com.agusteam.travelo.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginModel(val email: String, val password: String)
