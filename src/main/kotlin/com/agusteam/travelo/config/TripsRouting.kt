package com.agusteam.travelo.config

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.models.CreateTripModel
import com.agusteam.travelo.domain.models.ErrorResponse
import com.agusteam.travelo.domain.models.FavoriteTripModel
import com.agusteam.travelo.domain.models.PaginatedTripModel
import com.agusteam.travelo.getCreateTripUseCase
import com.agusteam.travelo.getPaginatedTripUseCase
import com.agusteam.travelo.getRemoveFavoriteTripUsecase
import com.agusteam.travelo.getSetFavoriteTripUsecase
import com.agusteam.travelo.getTripIncludeServicesUsecase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureTripsRouting() {
    routing {
        get("/trip/included/{trip}") {
            val tripId = call.parameters["trip"] ?: ""
            val useCase = getTripIncludeServicesUsecase()
            when (val result = useCase(tripId)) {

                is OperationResult.Success<*> -> {
                    val services = result.data as List<String>
                    call.respond(
                        HttpStatusCode.OK, services
                    )


                }

                is OperationResult.Error<*> -> {
                    val errorResponse = ErrorResponse(
                        status = HttpStatusCode.BadRequest.value,
                        error = "Bad Request",
                        message = result.exception.localizedMessage ?: "An error occurred"
                    )
                    call.respond(
                        HttpStatusCode.BadRequest,
                        errorResponse
                    )
                }
            }
        }
        get("/trip") {
            val useCase = getPaginatedTripUseCase()
            when (val result = useCase()) {
                is OperationResult.Error<*> -> {
                    val errorResponse = ErrorResponse(
                        status = HttpStatusCode.BadRequest.value,
                        error = "Bad Request",
                        message = result.exception.localizedMessage ?: "An error occurred"
                    )
                    call.respond(
                        HttpStatusCode.BadRequest,
                        errorResponse
                    )

                }

                is OperationResult.Success<*> -> {
                    val trips = result.data as List<PaginatedTripModel>
                    call.respond(
                        HttpStatusCode.OK, trips
                    )
                }
            }

        }
        delete("trip/favorite") {
            val request = call.receive<FavoriteTripModel>()
            val useCase = getRemoveFavoriteTripUsecase()
            when (val result = useCase(request)
            ) {
                is OperationResult.Error<*> -> {
                    val errorResponse = ErrorResponse(
                        status = HttpStatusCode.BadRequest.value,
                        error = "Bad Request",
                        message = result.exception.localizedMessage ?: "An error occurred"
                    )
                    call.respond(
                        HttpStatusCode.BadRequest, errorResponse
                    )
                }


                is OperationResult.Success<*> -> {
                    call.respond(
                        HttpStatusCode.OK, true

                    )
                }
            }
        }
        post("trip/favorite") {
            val request = call.receive<FavoriteTripModel>()
            val useCase = getSetFavoriteTripUsecase()
            when (val result = useCase(request)
            ) {
                is OperationResult.Error<*> -> {
                    val errorResponse = ErrorResponse(
                        status = HttpStatusCode.BadRequest.value,
                        error = "Bad Request",
                        message = result.exception.localizedMessage ?: "An error occurred"
                    )
                    call.respond(
                        HttpStatusCode.BadRequest, errorResponse
                    )
                }


                is OperationResult.Success<*> -> {
                    call.respond(
                        HttpStatusCode.OK, true

                    )
                }
            }
        }
        get("/trips") {
            val request = call.receive<CreateTripModel>()
            val useCase = getCreateTripUseCase()
            when (val result = useCase(request)) {
                is OperationResult.Error<*> -> {
                    val errorResponse = ErrorResponse(
                        status = HttpStatusCode.BadRequest.value,
                        error = "Bad Request",
                        message = result.exception.localizedMessage ?: "An error occurred"
                    )
                    call.respond(
                        HttpStatusCode.BadRequest,
                        errorResponse
                    )

                }

                is OperationResult.Success<*> -> {
                    call.respond(
                        HttpStatusCode.OK
                    )
                }
            }
        }
    }
}