package de.nicidienase.geniesser_app.api.fccampus

import com.google.gson.annotations.SerializedName

data class DayMenuDto(
    var day: String,
    var mealIds: List<String>,
    var meals: List<MealDto>
)

data class MealDto(
    var allergens: List<String>,
    @SerializedName("_id")
    var id: String,
    var name: String,
    var category: String,
    var price: Float,
    var uuid: String
)
