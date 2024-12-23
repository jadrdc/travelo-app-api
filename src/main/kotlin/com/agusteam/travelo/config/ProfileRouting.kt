package com.agusteam.travelo.config

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.models.UserProfileDetailsModel
import com.agusteam.travelo.domain.models.UserProfileModel
import com.agusteam.travelo.getGetProfileDetailsUseCase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureProfileRouting() {
    routing {
        post("/profile") {
            val request = call.receive<UserProfileModel>()
            val useCase = getGetProfileDetailsUseCase()
            val result = useCase(request.userId)
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
    }
}
