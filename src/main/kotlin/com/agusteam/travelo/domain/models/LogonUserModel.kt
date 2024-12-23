package com.agusteam.travelo.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class LogonUserModel(val id: String, val email: String)
