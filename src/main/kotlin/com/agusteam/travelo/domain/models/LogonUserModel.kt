package com.agusteam.travelo.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class LogonUserModel(val id: String, val email: String, val phone: String, val name: String, val lastname: String)
