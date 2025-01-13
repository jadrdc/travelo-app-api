package com.agusteam.travelo.data.payment

import com.agusteam.travelo.domain.models.StripePaymentRequest

class StripePaymentManager {

    fun createPaymentIntentConfiguration(request: StripePaymentRequest) {

        /*     val paymentIntentParams =
                 PaymentIntentCreateParams.builder()
                     .setAmount(request.amount)
                     .setCurrency("dop")
                     // In the latest version of the API, specifying the `automatic_payment_methods` parameter
                     // is optional because Stripe enables its functionality by default.
                     .setAutomaticPaymentMethods(
                         PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                             .setEnabled(true)
                             .build()
                     )
                     .build()

             val paymentIntent = PaymentIntent.create(paymentIntentParams)

             val responseData =  HashMap()
             responseData.put("paymentIntent", paymentIntent.getClientSecret())
             responseData.put(
                 "publishableKey",
                 "pk_test_51QXqqtJyClKQPN2TFutzGFEqZAmG9zO6ftbN0A380KxuR9B1fAvXB09vF5gbyWGD7o5oYwGIIQwKIupTwaqLScTC00bLaLNM8C"
                 "pk_test_51QXqqtJyClKQPN2TFu...TwaqLScTC00bLaLNM8C"
             )

             gson.toJson(responseData)*/

    }
}