package de.nicidienase.geniesser_app.data

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.microsoft.appcenter.crashes.Crashes
import de.nicidienase.geniesser_app.api.NewsDto
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Keep
@Entity(indices = [Index(value = ["title"], unique = true)])
data class News(
    var title: String,
    var date: Date,
    var content: String,
    var internal: Boolean,
    var imageUrl: String,
    var locationId: Long,
    var backendId: Long,
    var newNews: Boolean = false
) : Parcelable {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        Date(parcel.readLong()),
        parcel.readString() ?: "",
        parcel.readByte() != 0.toByte(),
        parcel.readString() ?: "",
        parcel.readLong(),
        parcel.readLong(),
        parcel.readByte() != 0.toByte()
    ) {
        id = parcel.readLong()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        with(parcel) {
            writeString(title)
            writeLong(date.time)
            writeString(content)
            writeByte(if (internal) 1 else 0)
            writeString(imageUrl)
            writeLong(id)
            writeLong(locationId)
            writeLong(backendId)
            writeByte(if (newNews) 1 else 0)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<News> {
        fun fromNewsDto(dto: NewsDto, locationId: Long): News? {
            val title = dto.title
            val date: Date? = try {
                dto.date?.let {
                    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.GERMANY).parse(it)
                }
            } catch (ex: Exception) {
                Crashes.trackError(ex)
                null
            }
            val content = dto.content
            val internal = dto.internal
            val imageUrl = dto.newsImageUrl
            val backendId = dto.id
            return if (
                !title.isNullOrEmpty() &&
                date != null &&
                !content.isNullOrBlank() &&
                !imageUrl.isNullOrBlank()
            ) News(title, date, content, false, imageUrl, locationId, backendId)
            else null
        }

        override fun createFromParcel(parcel: Parcel): News {
            return News(parcel)
        }

        override fun newArray(size: Int): Array<News?> {
            return arrayOfNulls(size)
        }
    }
}
