package de.nicidienase.geniesser_app.data

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import de.nicidienase.geniesser_app.api.ZusatzStoffDto

@Keep
@Entity(indices = [Index(value = ["additiveId"], unique = true)])
class Additive(
    var additiveId: Int,
    var name: String,
    var abbreviation: String
) : Parcelable {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(additiveId)
        parcel.writeString(name)
        parcel.writeString(abbreviation)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Additive> {
        override fun createFromParcel(parcel: Parcel): Additive {
            return Additive(parcel)
        }

        override fun newArray(size: Int): Array<Additive?> {
            return arrayOfNulls(size)
        }

        fun fromDto(dto: ZusatzStoffDto): Additive? {
            val id = dto.zusatzstoffeID
            val name = dto.name
            val abbreviation = dto.kuerzel
            return if (id != null &&
                !name.isNullOrEmpty() &&
                !abbreviation.isNullOrEmpty())
                Additive(id, name, abbreviation)
            else null
        }
    }
}