package com.agusteam.travelo.config

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.ktor.server.application.Application

fun Application.configureSupaBase() {
    val url = environment.config.propertyOrNull("ktor.deployment.projectUrl")?.getString() ?: ""
    val key = environment.config.propertyOrNull("ktor.deployment.apiKey")?.getString() ?: ""
    val supabase = createSupabaseClient(
        supabaseUrl = url,
        supabaseKey = key
    ) {
        install(Auth)
        install(Postgrest)
    }
}
