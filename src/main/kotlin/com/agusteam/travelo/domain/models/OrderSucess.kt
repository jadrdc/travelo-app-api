package com.agusteam.travelo.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class OrderSucess(val order:String,val transactionId:String)
