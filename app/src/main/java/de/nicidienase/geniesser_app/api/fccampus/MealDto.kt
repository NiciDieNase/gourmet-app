package de.nicidienase.geniesser_app.api.fccampus

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * Meal from FC-API
 */

@Keep
data class MealDto(
    var allergens: List<String>,
    @SerializedName("_id")
    var id: String?,
    var name: String,
    var category: String,
    var price: Float,
    var uuid: String
)
