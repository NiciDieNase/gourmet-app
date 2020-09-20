package de.nicidienase.geniesser_app.api.fccampus

import androidx.annotation.Keep

@Keep
data class DayMenuDto(
    var day: String,
    var mealIds: List<String>,
    var meals: List<MealDto>
)
