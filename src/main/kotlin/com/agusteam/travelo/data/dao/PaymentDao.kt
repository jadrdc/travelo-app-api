package com.agusteam.travelo.data.dao

import com.agusteam.travelo.domain.models.OrderFailureModel
import com.agusteam.travelo.domain.models.OrderModel
import com.agusteam.travelo.domain.models.TripScheduleQuorumModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class PaymentDao(supabase: SupabaseClient) {
    val db = supabase.postgrest


    suspend fun processOrderFailure(order: OrderFailureModel): Boolean {
        val params = buildJsonObject {
            put("order_id", order.order_id)
            put("reason", order.reason)
            put("tripscheduled", order.tripscheduled)
        }
        db.rpc("process_order_failure", parameters = params) {
        }
        return true
    }

    suspend fun processOrderSucess(order: String): Boolean {
        val params = buildJsonObject {
            put("order_id", order)
        }
        db.rpc("process_order_sucess", parameters = params) {
        }
        return true
    }

    suspend fun processOrder(order: OrderModel): String {
        val details = getTripSchedule(order.trip_schedule_id)
        if (details != null) {
            if (details.available > 0) {
                val params = buildJsonObject {
                    put("customer", order.customer_id)
                    put("tripscheduled", order.trip_schedule_id)
                }

                return db.rpc("process_payment_pending", parameters = params) {
                }.data.replace("\"", "")


            } else {
                throw Exception("Trip has not available space for reserving")
            }
        } else {
            throw Exception("Trip was not found")
        }
    }

    private suspend fun getTripSchedule(id: String): TripScheduleQuorumModel? {
        return db.from("trip_scheduled").select(columns = Columns.raw("quorum,id,available")) {
            filter {
                eq("id", id) // Filter based on the business_id in tripModel
            }
        }.decodeSingleOrNull<TripScheduleQuorumModel>()
    }

    private suspend fun createOrder(order: OrderModel): Boolean {
        return try {
            val reult = db.from("orders").insert(order)
            true
        } catch (e: Exception) {
            false
        }
    }
}