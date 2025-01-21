package com.agusteam.travelo.config

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.models.*
import com.agusteam.travelo.getSignUpUseCase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureSignUpFlowApi() {
    routing {
        post("/resetPasswordForEmail") {
            val request = call.receive<RequestPasswordChangeModel>()
            val useCase = getSignUpUseCase()
            when (val result = useCase.resetPasswordForEmail(request)) {
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

                is OperationResult.Success<*> -> call.respond(
                    HttpStatusCode.OK,
                    "Request for  sending email sent: ${request.email}"
                )

            }
        }
        post("/confirmEmail") {
            val request = call.receive<ConfirmEmailModel>()
            val useCase = getSignUpUseCase()
            when (val result = useCase.confirmEmail(request)) {
                is OperationResult.Error<*> -> call.respond(
                    HttpStatusCode.BadRequest,
                    result.exception.localizedMessage
                )

                is OperationResult.Success<*> -> call.respond(
                    HttpStatusCode.OK,
                    "Request for  sending email sent: ${request.userId}"
                )

            }
        }

        post("/login") {
            try {
                val request = call.receive<LoginModel>()
                val useCase = getSignUpUseCase()
                when (val result = useCase.login(request)) {
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

                    is OperationResult.Success<*> -> call.respond(
                        HttpStatusCode.OK, result.data as LogonUserModel
                    )

                }
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Error: ${e.message}")
            }
        }
        post("/signup") {
            try {
                val request = call.receive<UserSignupModel>()
                val useCase = getSignUpUseCase()
                when (val result = useCase.signUpUser(request)) {
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

                    is OperationResult.Success<*> -> call.respond(
                        HttpStatusCode.OK,
                        "User registered successfully: ${request.name}"
                    )

                }
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Error: ${e.message}")
            }
        }

    }
}
