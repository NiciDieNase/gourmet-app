package de.nicidienase.geniesser_app.data

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import de.nicidienase.geniesser_app.api.fccampus.MealDto
import java.util.Date

@Keep
@Entity(indices = [Index(value = ["apiId"], unique = true)])
data class FcMeal(
    var name: String,
    var category: String,
    var price: Int,
    var uuid: String,
    var date: Date,
    var apiId: String = ""
// allergens: List<String>
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    companion object {
        fun fromMealDto(dto: MealDto, date: Date): FcMeal? {
            return FcMeal(
                dto.name,
                dto.category,
                (dto.price * 100).toInt(),
                dto.uuid,
                date,
                dto.id ?: ""
            )
        }
    }
}
