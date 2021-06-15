package de.nicidienase.geniesser_app.api.fccampus

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MealTimesWrapperDto(
    @SerializedName("_id")
    var id: String,
    var mealTimes: List<MealTimeDto>
)
