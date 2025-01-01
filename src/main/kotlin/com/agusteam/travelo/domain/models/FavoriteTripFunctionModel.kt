package com.agusteam.travelo.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class FavoriteTripFunctionModel(val userid: String, val tripid: String)

@Serializable
data class RpcParameters(val userid: String, val tripid: String)