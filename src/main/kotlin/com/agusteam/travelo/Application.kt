package com.agusteam.travelo

import com.agusteam.travelo.config.configureCategoryRouting
import com.agusteam.travelo.config.configureDI
import com.agusteam.travelo.config.configureProfileRouting
import com.agusteam.travelo.config.configureSignUpFlowApi
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*


fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(StatusPages) {
        exception<IllegalStateException> { call, cause ->
            call.respondText("App in illegal state as ${cause.message}")
        }
    }
    install(ContentNegotiation) {
        json()  // Use Kotlinx Serialization with JSON format
    }
    configureDI()
    configureSignUpFlowApi()
    configureProfileRouting()
    configureCategoryRouting()
}
