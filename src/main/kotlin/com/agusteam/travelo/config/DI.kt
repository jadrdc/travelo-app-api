package com.agusteam.travelo.config

import com.agusteam.travelo.data.dao.UserProfileDao
import com.agusteam.travelo.data.impl.UserSignUpRepositoryImp
import com.agusteam.travelo.data.validations.FieldValidator
import com.agusteam.travelo.domain.interfaces.UserSignUpRepository
import com.agusteam.travelo.domain.usecase.SignUpUserUseCase
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureDI() {
    val url = environment.config.propertyOrNull("ktor.deployment.projectUrl")?.getString() ?: ""
    val key = environment.config.propertyOrNull("ktor.deployment.apiKey")?.getString() ?: ""
    install(Koin) {
        slf4jLogger() // Optional logger
        modules(dInjectionModule)
        properties(mapOf("supabaseUrl" to url, "supabaseKey" to key))
    }
}

val dInjectionModule = module {
    // SupabaseClient dependency
    single<SupabaseClient> { (supabaseUrl: String, supabaseKey: String) ->
        createSupabaseClient(
            supabaseUrl = supabaseUrl,
            supabaseKey = supabaseKey
        ) {
            install(Auth)
            install(Postgrest)
        }
    }
    single<FieldValidator> { FieldValidator() }
    single<UserProfileDao> { UserProfileDao(get()) }
    single<UserSignUpRepository> { UserSignUpRepositoryImp(get(), get()) }
    single<SignUpUserUseCase> { SignUpUserUseCase(get(), get()) }


}
