package com.agusteam.travelo.data.dao

import com.agusteam.travelo.domain.models.BusinessProfileModel
import com.agusteam.travelo.domain.models.CreateBusinessProfileModel
import com.agusteam.travelo.domain.models.UpcomingTripModelListResponse
import com.agusteam.travelo.domain.models.UserProfileDetailsModel
import com.agusteam.travelo.models.TripsID
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns

class UserProfileDao(supabase: SupabaseClient) {
    val db = supabase.postgrest

    suspend fun userExists(email: String): Boolean {
        val result = db.from("user_profile").select {
            filter {
                UserProfileDetailsModel::email eq email
            }
        }.decodeList<UserProfileDetailsModel>()

        return result.isNotEmpty()
    }

    suspend fun insertUserProfile(userDetails: UserProfileDetailsModel) {
        db.from("user_profile").insert(userDetails)
    }

    suspend fun getUserProfile(id: String): UserProfileDetailsModel? {

        return db.from("user_profile").select(columns = Columns.list("id", "name", "email", "phone", "lastname")) {
            filter {
                UserProfileDetailsModel::id eq id
            }
        }.decodeSingleOrNull<UserProfileDetailsModel>()
    }

    suspend fun getBusinessProfile(id: String): List<BusinessProfileModel> {
        val columns = Columns.raw(
            """ user_business_id, category_id,business:user_business(id,name,phone,email,description,rnc,image,address,created_at),
      category:categories(id,description,image,is_active)
        """.trimIndent()
        )

        val results = db.from("user_business_category").select(
            columns = columns
        ) {
            filter {
                BusinessProfileModel::user_business_id eq id
            }
        }.decodeList<BusinessProfileModel>()

        return results.map {
            it.copy(tripOffers = 0)

        }
    }

    suspend fun insertBusinessProfile(businessProfileModel: CreateBusinessProfileModel) {
        db.from("user_business").insert(businessProfileModel)
    }

    suspend fun getOfferTripsByProvider(provider: String): Int {
        val columns = Columns.raw(
            """ id,name,description,lat,lng,destiny
        """.trimIndent()
        )
        return try {
            val result = db.from("trips").select(columns) {
                filter { eq("business_id", provider) }
            }.decodeList<TripsID>().size

            result
        } catch (e: Exception) {
            0
        }
    }


    suspend fun getTotalActiveTrips(provider: String): Int {
        return try {
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
                filter {
                    eq("trip_id.business_id", provider) // Filter based on the business_id in tripModel
                    eq("is_active", true) // Filter based on the business_id in tripModel
                }
            }.decodeList<UpcomingTripModelListResponse>().filter { it.tripModel != null }.size

            result
        } catch (e: Exception) {
            0
        }
    }
}