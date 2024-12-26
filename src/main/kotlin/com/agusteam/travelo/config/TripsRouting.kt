package com.agusteam.travelo.config

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.models.CategoryModel
import com.agusteam.travelo.domain.models.CreateTripModel
import com.agusteam.travelo.domain.models.ErrorResponse
import com.agusteam.travelo.domain.models.PaginatedTripModel
import com.agusteam.travelo.domain.models.TripsModel
import com.agusteam.travelo.getCategoriesUseCase
import com.agusteam.travelo.getCreateTripUseCase
import com.agusteam.travelo.getPaginatedTripUseCase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*


fun Application.configureTripsRouting() {
    routing {
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