package com.agusteam.travelo

import com.agusteam.travelo.config.configureRouting
import com.agusteam.travelo.config.configureSupaBase
import io.ktor.server.application.*


fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureRouting()
    configureSupaBase()
}
