package com.agusteam.travelo.config

import com.agusteam.travelo.data.core.OperationResult
import com.agusteam.travelo.getCategoriesUseCase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*


fun Application.configureCategoryRouting() {
    routing {
      /*  get("/categories") {
            val useCase = getCategoriesUseCase()
            when (val result = useCase()) {
                is OperationResult.Error<*> -> {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        result.exception.localizedMessage
                    )
                }

                is OperationResult.Success<*> -> {
                    val categories = result.data as List<*>
                    call.respond(
                        HttpStatusCode.OK, categories
                    )
                }
            }
        }*/
    }
}