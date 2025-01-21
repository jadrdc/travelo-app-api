package com.agusteam.travelo.config

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.data.dao.OrdersDao
import com.agusteam.travelo.data.impl.OrderRepositoryImp
import com.agusteam.travelo.domain.models.ErrorResponse
import com.agusteam.travelo.domain.models.UpcomingOrderTrip
import com.agusteam.travelo.domain.usecase.GetUpcomingOrderTrip
import com.agusteam.travelo.getAdminSupaBase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.orderRouting() {
    routing {
        get("/upcomingOrdersTrips/{user}") {
            val req = call.parameters["user"] ?: ""
            if (req.isBlank()) {
                call.respond(
                    HttpStatusCode.BadRequest,
                    "Missing fiels"
                )
            } else {
                val useCase = GetUpcomingOrderTrip(OrderRepositoryImp(OrdersDao(getAdminSupaBase())))
                try {
                    when (val result = useCase.getOrdersUpcomingTrip(req)) {
                        is OperationResult.Error<*> -> {
                            val errorResponse = ErrorResponse(
                                status = HttpStatusCode.InternalServerError.value,
                                error = "Bad Request",
                                message = result.exception.localizedMessage ?: "An error occurred"
                            )
                            call.respond(
                                HttpStatusCode.BadRequest,
                                errorResponse
                            )
                        }

                        is OperationResult.Success<*> -> {
                            val orders = result.data as List<UpcomingOrderTrip>
                            call.respond(
                                HttpStatusCode.OK, orders
                            )
                        }
                    }
                } catch (e: Exception) {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        e.localizedMessage
                    )
                }
            }
        }
        get("/pastTrips/{user}") {
            val req = call.parameters["user"] ?: ""
            if (req.isBlank()) {
                call.respond(
                    HttpStatusCode.BadRequest,
                    "Missing fiels"
                )
            } else {
                val useCase = GetUpcomingOrderTrip(OrderRepositoryImp(OrdersDao(getAdminSupaBase())))
                try {
                    when (val result = useCase.getPastTrips(req)) {
                        is OperationResult.Error<*> -> {
                            val errorResponse = ErrorResponse(
                                status = HttpStatusCode.InternalServerError.value,
                                error = "Bad Request",
                                message = result.exception.localizedMessage ?: "An error occurred"
                            )
                            call.respond(
                                HttpStatusCode.BadRequest,
                                errorResponse
                            )
                        }

                        is OperationResult.Success<*> -> {
                            val orders = result.data as List<UpcomingOrderTrip>
                            call.respond(
                                HttpStatusCode.OK, orders
                            )
                        }
                    }
                } catch (e: Exception) {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        e.localizedMessage
                    )
                }
            }
        }
    }
}