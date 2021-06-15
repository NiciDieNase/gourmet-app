package de.nicidienase.geniesser_app.api.fccampus

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MealTimeDto(
    @SerializedName("_id")
    var id: String,
    var calendarWeek: Int,
    var description: String,
    var from: String,
    var to: String
)
