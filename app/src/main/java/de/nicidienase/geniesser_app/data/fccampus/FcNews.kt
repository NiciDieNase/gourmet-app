package de.nicidienase.geniesser_app.data.fccampus

import androidx.room.Entity
import androidx.room.PrimaryKey
import de.nicidienase.geniesser_app.api.fccampus.FcNewsDto
import de.nicidienase.geniesser_app.util.CalendarUtils
import java.util.Date

@Entity
data class FcNews(
    val apiId: String,
    val title: String,
    val notificationText: String,
    val newsText: String,
    val date: Date,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
) {
    companion object {

        fun fromDto(dto: FcNewsDto): FcNews? {
            val date = CalendarUtils.parseDateString(dto.date)
            return if (date != null) {
                FcNews(
                    dto.id,
                    dto.title,
                    dto.notoficationText,
                    dto.newsText,
                    date
                )
            } else null
        }
    }
}
