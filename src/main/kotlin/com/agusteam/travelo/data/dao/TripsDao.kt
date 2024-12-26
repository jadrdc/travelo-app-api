package com.agusteam.travelo.data.dao

import com.agusteam.travelo.domain.models.CreateTripModel
import com.agusteam.travelo.domain.models.PaginatedTripModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns

class TripsDao(supabase: SupabaseClient) {
    val db = supabase.postgrest

    suspend fun getTripsPagination(): List<PaginatedTripModel> {
        //TODO ADD FILTRO PARA CATEGORIS
        //TODO ADD PAGINATION 10
        // ADD FILTER PARA FECHA
        val columns = Columns.raw(
            """id,name,description,destiny,lat,lng,businessModel:user_business(id,name,phone,email,description,rnc,image,address,created_at)
        """.trimIndent()
        )
        return db.from("trips").select(
            columns = columns
        ).decodeList<PaginatedTripModel>()

    }

    suspend fun insertTrip(model: CreateTripModel) {
        db.from("trips").insert(model)
    }
}
