package com.agusteam.travelo.config

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.mappers.mapToTripProfileModel
import com.agusteam.travelo.domain.models.*
import com.agusteam.travelo.geBusinessProfileDetailsUseCase
import com.agusteam.travelo.getGetBusinessProfileUseCase
import com.agusteam.travelo.getGetProfileDetailsUseCase
import com.agusteam.travelo.getGetUpcomingTripsByModelUseCase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureProfileRouting() {
    routing {

        get("/getBusinessUpcomingTrips/{id}") {
            val request = call.parameters["id"] ?: ""
            val useCase = getGetUpcomingTripsByModelUseCase()
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
                        HttpStatusCode.OK,
                        result.data as List<UpcomingTripModelListResponse>
                    )
                }
            }
        }
        get("/profile/{id}") {
            val request = call.parameters["id"] ?: ""
            val useCase = getGetProfileDetailsUseCase()
            val result = useCase(request)
            when (result) {
                is OperationResult.Error<*> -> call.respond(
                    HttpStatusCode.BadRequest,
                    result.exception.localizedMessage
                )

                is OperationResult.Success<*> -> call.respond(
                    HttpStatusCode.OK,
                    result.data as UserProfileDetailsModel
                )

            }
        }
        post("/business") {
            val request = call.receive<CreateBusinessProfileModel>()
            val useCase = geBusinessProfileDetailsUseCase()
            val result = useCase(request)
            when (result) {
                is OperationResult.Error<*> -> call.respond(
                    HttpStatusCode.BadRequest,
                    result.exception.localizedMessage
                )

                is OperationResult.Success<*> -> call.respond(
                    HttpStatusCode.OK,
                    result.data as CreateBusinessProfileModel
                )
            }
        }
        post("/businessProfile") {
            val request = call.receive<GetBusinessProfileModel>()
            val useCase = getGetBusinessProfileUseCase()
            val result = useCase(request.id)
            when (result) {
                is OperationResult.Error<*> -> call.respond(
                    HttpStatusCode.BadRequest,
                    result.exception.localizedMessage
                )

                is OperationResult.Success<*> -> {
                    val trip = result.data as List<BusinessProfileModel>
                    call.respond(
                        HttpStatusCode.OK, mapToTripProfileModel(trip)
                    )
                }
            }
        }
    }
}
