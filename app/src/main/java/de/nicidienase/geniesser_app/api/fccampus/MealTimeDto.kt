package de.nicidienase.geniesser_app.api.fccampus

import com.google.gson.annotations.SerializedName

data class MealTimeDto(
    @SerializedName("_id")
    var id: String,
    var calendarWeek: Int,
    var description: String,
    var from: String,
    var to: String
)
