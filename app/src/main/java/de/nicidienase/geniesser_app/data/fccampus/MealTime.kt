package de.nicidienase.geniesser_app.data.fccampus

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import de.nicidienase.geniesser_app.api.fccampus.MealTimeDto
import de.nicidienase.geniesser_app.util.CalendarUtils
import java.util.Date

@Keep
@Entity(indices = [Index(value = ["apiId"], unique = true)])
data class MealTime(
    var calendarWeek: Int,
    var description: String,
    var from: Date,
    var to: Date,
    var apiId: String = ""
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    companion object {
        fun fromMealTimeDto(dto: MealTimeDto): MealTime? {
            val fromDate = CalendarUtils.parseDateString(dto.from)
            val toDate = CalendarUtils.parseDateString(dto.to)

            return if (fromDate != null && toDate != null) {
                MealTime(
                    calendarWeek = dto.calendarWeek,
                    description = dto.description,
                    from = fromDate,
                    to = toDate,
                    apiId = dto.id
                )
            } else null
        }
    }
}
