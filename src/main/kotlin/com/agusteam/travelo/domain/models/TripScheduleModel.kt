package com.agusteam.travelo.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class TripScheduleModel(
    val tripModel: TripsModel
) {
    fun toPaginatedTripCategoryModel(): List<PaginatedTripCategoryModel> {
        return tripModel.scheduledModel.map { data ->
            PaginatedTripCategoryModel(
                id = tripModel.id,
                name = tripModel.name,
                description = tripModel.description,
                destiny = tripModel.destiny,
                lat = tripModel.lat,
                lng = tripModel.lng,
                images = tripModel.images,
                cancellation_policy = tripModel.cancellation_policy,
                businessModel = tripModel.businessModel,
                details = TripDetails(
                    initial_payment = data.initial_payment,
                    returning_time = data.returning_time,
                    leaving_time = data.leaving_time,
                    is_active = true,
                    meeting_point = data.meeting_point,
                    total_payment = data.total_payment
                )
            )
        }

    }
}