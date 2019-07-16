package de.nicidienase.geniesser_app.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import de.nicidienase.geniesser_app.api.SpeiseplanKategorieDto

@Entity
class Category(var categoryId: Int, var name: String) : Parcelable {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0


    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(categoryId)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Category> {
        override fun createFromParcel(parcel: Parcel): Category {
            return Category(parcel)
        }

        override fun newArray(size: Int): Array<Category?> {
            return arrayOfNulls(size)
        }

        fun fromDto(dto: SpeiseplanKategorieDto): Category? {
            val categoryId = dto.gerichtkategorieID
            val name = dto.name
            return if (categoryId != null && !name.isNullOrEmpty()) {
                Category(categoryId, name)
            } else {
                null
            }
        }
    }
}