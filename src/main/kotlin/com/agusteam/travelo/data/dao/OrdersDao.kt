package com.agusteam.travelo.data.dao

import com.agusteam.travelo.domain.models.UpcomingOrderTrip
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns

class OrdersDao(supabase: SupabaseClient) {
    private val db = supabase.postgrest

    suspend fun getUpcomingTrips(userId: String): List<UpcomingOrderTrip> {
        val columns = Columns.raw(
            """ 
                id,
                status,customer_id,
                tripScheduleModel:trip_schedule_id(
                is_active,total_payment,
                returning_time,
                leaving_time,
                tripModel:trip_id(name,destiny,images, businessModel:business_id(name,image,created_at)))
        """
        )

        return db.from("orders").select(
            columns = columns
        ) {
            filter {
                eq("trip_schedule_id.is_active", true) // Filter based on the business_id in tripModel
                eq("status", "success") // Filter based on the business_id in tripModel
                eq("customer_id", userId) // Filter based on the business_id in tripModel
            }
        }.decodeList<UpcomingOrderTrip>().filter { it.tripScheduleModel?.is_active == true }

    }

    suspend fun getPastTrips(userId: String): List<UpcomingOrderTrip> {
        val columns = Columns.raw(
            """  id,
                status,customer_id,
                tripScheduleModel:trip_schedule_id(
                total_payment,
                is_active,
                returning_time,
                leaving_time,
                tripModel:trip_id(name,destiny,images, businessModel:business_id(name,image,created_at)))
        """
        )

        val result = db.from("orders").select(
            columns = columns
        ) {
            filter {
                eq("trip_schedule_id.is_active", false) // Filter based on the business_id in tripModel
                eq("status", "success") // Filter based on the business_id in tripModel
                eq("customer_id", userId) // Filter based on the business_id in tripModel
            }
        }

        return result.decodeList<UpcomingOrderTrip>().filter { it.tripScheduleModel != null }

    }
}