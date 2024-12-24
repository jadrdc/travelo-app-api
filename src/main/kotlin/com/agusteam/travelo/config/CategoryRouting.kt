package com.agusteam.travelo.config

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.domain.models.CategoryModel
import com.agusteam.travelo.domain.models.ErrorResponse
import com.agusteam.travelo.getCategoriesUseCase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.respond
import io.ktor.server.routing.*


fun Application.configureCategoryRouting() {
    routing {
        get("/categories") {
            val useCase = getCategoriesUseCase()
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
                    val categories = result.data as List<CategoryModel>
                    call.respond(
                        HttpStatusCode.OK, categories
                    )
                }
            }
        }
    }
}