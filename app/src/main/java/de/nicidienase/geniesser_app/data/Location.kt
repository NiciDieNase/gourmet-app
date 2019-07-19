package de.nicidienase.geniesser_app.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import de.nicidienase.geniesser_app.api.StandortDto

@Entity(indices = [Index(value = ["locationId"], unique = true)])
data class Location(
    var locationId: Int,
    var name: String
) : Parcelable {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(locationId)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Location> {
        override fun createFromParcel(parcel: Parcel): Location {
            return Location(parcel)
        }

        override fun newArray(size: Int): Array<Location?> {
            return arrayOfNulls(size)
        }

        fun fromDto(dto: StandortDto): Location? {
            val id = dto.id
            val name = dto.name
            return if (id != null && !name.isNullOrEmpty())
                Location(id, name)
            else null
        }
    }
}