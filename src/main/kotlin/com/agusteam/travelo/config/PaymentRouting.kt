package com.agusteam.travelo.config

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.data.dao.PaymentDao
import com.agusteam.travelo.data.impl.PaymentRepositoryImp
import com.agusteam.travelo.domain.models.*
import com.agusteam.travelo.domain.usecase.ProccessOrderFailureUseCase
import com.agusteam.travelo.domain.usecase.ProccessOrderSucessUseCase
import com.agusteam.travelo.domain.usecase.ProccessOrderTripUseCase
import com.agusteam.travelo.getAdminSupaBase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configurePaymentRouting() {
    routing {
        post("/pending-order") {
            val request = call.receive<ProcessPendingOrderRequestModel>()
            if (request.tripDetail.isBlank() || request.customer.isBlank()) {
                call.respond(HttpStatusCode.BadRequest, "missing informationx¬")

            }
            val useCase = ProccessOrderTripUseCase(PaymentRepositoryImp(PaymentDao(getAdminSupaBase())))

            when (val result = useCase(request.toOrder())) {
                is OperationResult.Success -> {
                    call.respond(
                        HttpStatusCode.OK,
                        PaymentPendingOrder(result.data ?: "")
                    )
                }

                is OperationResult.Error -> {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        result.exception.localizedMessage
                    )
                }
            }
        }
        post("/order-failure") {
            val request = call.receive<ProcessOrderFailureRequest>()
            if (request.order.isBlank() || request.reason.isBlank() || request.tripscheduled.isBlank()) {
                call.respond(HttpStatusCode.BadRequest, "missing informationx¬")

            }
            val useCase = ProccessOrderFailureUseCase(PaymentRepositoryImp(PaymentDao(getAdminSupaBase())))

            when (val result = useCase(request.toDomain())) {
                is OperationResult.Success -> {
                    call.respond(
                        HttpStatusCode.OK, true
                    )
                }

                is OperationResult.Error -> {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        result.exception.localizedMessage
                    )
                }
            }
        }
        post("/order-sucess") {
            val request = call.receive<OrderSucess>()
            if (request.order.isBlank()) {
                call.respond(HttpStatusCode.BadRequest, "missing informationx¬")

            }
            val useCase = ProccessOrderSucessUseCase(PaymentRepositoryImp(PaymentDao(getAdminSupaBase())))

            when (val result = useCase(request.order,request.transactionId)) {
                is OperationResult.Success -> {
                    call.respond(
                        HttpStatusCode.OK, true
                    )
                }

                is OperationResult.Error -> {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        result.exception.localizedMessage
                    )
                }
            }
        }
    }
}