package com.agusteam.travelo.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class StripePaymentRequest(val amount: Long)
