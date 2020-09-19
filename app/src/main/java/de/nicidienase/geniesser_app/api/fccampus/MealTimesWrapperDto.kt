package de.nicidienase.geniesser_app.api.fccampus

import com.google.gson.annotations.SerializedName

data class MealTimesWrapperDto(
    @SerializedName("_id")
    var id: String,
    var mealTimes: List<MealTimeDto>
)
