package de.nicidienase.geniesser_app.data

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import de.nicidienase.geniesser_app.api.NewsDto
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Keep
@Entity(indices = [Index(value = ["title"], unique = true)])
data class News(
    val title: String,
    val date: Date,
    val content: String,
    val internal: Boolean,
    val imageUrl: String,
    val locationId: Long,
    val backendId: Long
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
        parcel.readLong()
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
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<News> {
        fun fromNewsDto(dto: NewsDto, locationId: Long): News? {
            val title = dto.title
            val date = dto.date?.let {
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.GERMANY).parse(it)
            }
            val content = dto.content
            val internal = dto.internal
            val imageUrl = dto.newsImageUrl
            val backendId = dto.id
            return if (
                !title.isNullOrEmpty() &&
                date != null &&
                !content.isNullOrBlank() &&
                internal != null &&
                !imageUrl.isNullOrBlank()
            ) News(title, date, content, internal, imageUrl, locationId, backendId)
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
