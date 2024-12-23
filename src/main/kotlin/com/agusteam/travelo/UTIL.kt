package com.agusteam.travelo

import com.agusteam.travelo.data.dao.UserProfileDao
import com.agusteam.travelo.data.impl.UserSignUpRepositoryImp
import com.agusteam.travelo.data.validations.FieldValidator
import com.agusteam.travelo.domain.usecase.SignUpUserUseCase
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

fun getSignUpUseCase(): SignUpUserUseCase {
    val supabase = createSupabaseClient(
        supabaseUrl = "https://fjuwfnwhfdtdlflnlmnp.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZqdXdmbndoZmR0ZGxmbG5sbW5wIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzQ3Mzc1MzUsImV4cCI6MjA1MDMxMzUzNX0.hpYG66JoG0_wWNsPv0b6nFcdiaGi-yodY-De0frik28"
    ) {
        install(Auth)
        install(Postgrest)
    }
    return SignUpUserUseCase(
        UserSignUpRepositoryImp(
            supabase = getAdminSupaBase(), userProfileDao = UserProfileDao(supabase)
        ), FieldValidator()
    )
}

fun getAdminSupaBase(): SupabaseClient {
    return createSupabaseClient(
        supabaseUrl = "https://fjuwfnwhfdtdlflnlmnp.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZqdXdmbndoZmR0ZGxmbG5sbW5wIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTczNDczNzUzNSwiZXhwIjoyMDUwMzEzNTM1fQ.A4P-gN48OnDRA7-NUb0_HxHm_7B2UMViUswhG3_C4do"
    ) {
        install(Auth)
        install(Postgrest)
    }

}