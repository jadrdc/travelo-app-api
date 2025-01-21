package com.agusteam.travelo.config

import com.agusteam.travelo.*
import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.data.dao.TripsDao
import com.agusteam.travelo.data.impl.TripRepositoryImp
import com.agusteam.travelo.domain.models.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureTripsRouting() {
    routing {
        post("/favorite/e") {
            val trp = TripRepositoryImp(TripsDao(getAdminSupaBase()))
            val result =
                trp.isFavoriteTrip("9811c69f-8b55-4128-b366-d917176e75d2",
                    "f2606221-18ca-452e-bf5a-205990c05fd4")
            when (result) {
                is OperationResult.Error -> {
                    val errorResponse = ErrorResponse(
                        status = HttpStatusCode.BadRequest.value,
                        error = "Bad Request",
                        message = result.exception.localizedMessage ?: "An error occurred"
                    )
                    call.respond(
                        HttpStatusCode.BadRequest, errorResponse
                    )
                }

                is OperationResult.Success -> {
                    call.respond(
                        HttpStatusCode.OK, result.data
                    )
                }
            }
        }

        post("/trip/availables") {
            val req = call.receive<TripAvailablePaginationRequestModel>()
            val useCase = getPaginatedTripDetailUseCase()

            when (val result = useCase(
                req
            )) {

                is OperationResult.Success<*> -> {
                    call.respond(
                        HttpStatusCode.OK, result.data as List<PaginatedTripCategoryModel>
                    )
                }

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
            }

        }
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
                        HttpStatusCode.BadRequest, errorResponse
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
                        HttpStatusCode.BadRequest, errorResponse
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
            when (val result = useCase(request)) {
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
        get("trip/favorite/{userId}") {
            val tripId = call.parameters["userId"] ?: ""
            val useCase = getGetFavoriteTripsUseCase()
            when (val result = useCase(tripId)) {

                is OperationResult.Success<*> -> {
                    val trips = result.data as List<PaginatedFavoriteTripModel>
                    call.respond(
                        HttpStatusCode.OK, trips

                    )
                }

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
            }

        }
        post("trip/favorite") {
            val request = call.receive<FavoriteTripModel>()
            val useCase = getSetFavoriteTripUsecase()
            when (val result = useCase(request)) {
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
                        HttpStatusCode.BadRequest, errorResponse
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