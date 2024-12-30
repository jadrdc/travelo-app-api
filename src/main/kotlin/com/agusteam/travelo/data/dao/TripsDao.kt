package com.agusteam.travelo.data.dao

import com.agusteam.travelo.domain.models.CreateTripModel
import com.agusteam.travelo.domain.models.FavoriteTripModel
import com.agusteam.travelo.domain.models.PaginatedTripModel
import com.agusteam.travelo.domain.models.TripIncludedServicesModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns

class TripsDao(supabase: SupabaseClient) {
    val db = supabase.postgrest

    suspend fun setFavoriteTrip(model: FavoriteTripModel) {
        db.from("favorite_trips").insert(model)
    }

    suspend fun getTripIncluded(trip_id: String): List<TripIncludedServicesModel> {
        return db.from("trips_included").select(columns = Columns.list("description")) {
            filter { eq("trip_id", trip_id) }
        }.decodeList<TripIncludedServicesModel>()
    }

    suspend fun removeFavorite(model: FavoriteTripModel) {
        db.from("favorite_trips").delete {
            filter {
                eq("trip_id", model.trip_id) // Specify the condition
                eq("user_id", model.user_id) // Specify the condition
            }
        }
    }

    suspend fun getTripsPagination(): List<PaginatedTripModel> {
        //TODO ADD FILTRO PARA CATEGORIS
        //TODO ADD PAGINATION 10
        // ADD FILTER PARA FECHA

        val columns = Columns.raw(
            """id, 
       name, 
       description, 
       destiny, 
       lat, 
       lng, 
       businessModel:user_business(id,name,phone,email,description,rnc,image,address,created_at),images,cancellation_policy"""
        )

        return db.from("trips").select(columns = columns) {
        }.decodeList<PaginatedTripModel>()
    }

    suspend fun insertTrip(model: CreateTripModel) {
        db.from("trips").insert(model)
    }
}
