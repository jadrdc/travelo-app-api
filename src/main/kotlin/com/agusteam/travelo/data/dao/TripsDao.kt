package com.agusteam.travelo.data.dao

import com.agusteam.travelo.domain.models.*
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.Order
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.json.buildJsonObject


class TripsDao(supabase: SupabaseClient) {
    val db = supabase.postgrest


    suspend fun getFavoriteTripList(userId: String): List<PaginatedFavoriteTripModel> {
        val columns = Columns.raw(
            """
         tripModel:trip_id(id,
                          name,
                           lat,
                           lng,
                           cancellation_policy,
                           description,
                          destiny,
                          images, 
                          business_id,
                          businessModel:business_id(id,name,phone,email,description,rnc,image,address,created_at) )
        """
        )
        return db.from("favorite_trips").select(columns).decodeList<PaginatedFavoriteTripModel>()

    }

    suspend fun getUpcomingTrips(businessId: String): List<UpcomingTripModelListResponse> {
        val columns = Columns.raw(
            """ 
           is_active,
           leaving_time,
           returning_time,
           total_payment,
         tripModel:trip_id(id,
                          name,
                          destiny,
                          images, 
                          business_id,
                          businessModel:business_id(id,name,phone,email,description,rnc,image,address,created_at) )
        """
        )

        val result = db.from("trip_scheduled").select(columns = columns) {
            limit(5)
            filter { UpcomingTripModelListResponse::is_active eq true }
            filter {
                eq("trip_id.business_id", businessId) // Filter based on the business_id in tripModel
            }
            order(column = "leaving_time", order = Order.ASCENDING)

        }.decodeList<UpcomingTripModelListResponse>().filter { it.tripModel != null }

        return result
    }


    suspend fun getActiveTrips(): List<TripScheduleModel> {
        //businessModel:user_business(id,name,phone,email,description,rnc,image,address,created_at),
        val columns = Columns.raw(
            """ 
           leaving_time,
           returning_time,
        total_payment,
        initial_payment,
        meeting_point,
        tripModel:trip_id(id,
       name,
       description,
       destiny,
       lat,
       lng,images,cancellation_policy, businessModel:business_id(id,name,phone,email,description,rnc,image,address,created_at) )"""
        )

        return db.from("trip_scheduled").select(columns = columns).decodeList<TripScheduleModel>()
    }

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

    suspend fun doesFavoriteTripExist(userId: String, tripId: String): String {
        val result = db.rpc("is_trip_favorite", parameters = buildJsonObject {
            /*  put("userid", userId)
              put(
                  "tripid", tripId
              )*/
        })
        return result.data
    }

    suspend fun getTripsPagination(): List<PaginatedTripModel> {
        val columns = Columns.raw(
            """id, 
        name, 
        description, 
        destiny, 
        lat, 
        lng, 
        businessModel:user_business(id,name,phone,email,description,rnc,image,address,created_at),images,cancellation_policy"""
        )

        val tripsResult = db.from("trips").select(columns = columns).decodeList<PaginatedTripModel>()
        return coroutineScope {
            tripsResult.map { trip ->
                async {
                    val isFavorite = doesFavoriteTripExist(
                        userId = "0d7f08cf-9a8d-47a3-9a30-37793374a2be",
                        tripId = "099bdb34-7cc5-45f4-9e1e-c02b97d6c6d2",
                    )
                    trip.copy(isFavorite = isFavorite)
                }
            }.awaitAll() // Wait for all async calls to complete and return the result
        }
    }


    suspend fun insertTrip(model: CreateTripModel) {
        db.from("trips").insert(model)
    }

}
