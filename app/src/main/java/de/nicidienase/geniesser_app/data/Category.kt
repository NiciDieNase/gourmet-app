package de.nicidienase.geniesser_app.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import de.nicidienase.geniesser_app.api.SpeiseplanKategorieDto

class Category(
    @SerializedName("categoryId") var categoryId: Int,
    @SerializedName("categoryName") var categoryName: String,
    @SerializedName("reihenfolgeInApp") var orderIndex: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(categoryId)
        parcel.writeString(categoryName)
        parcel.writeInt(orderIndex)
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
            val order = dto.reihenfolgeInApp
            return if (categoryId != null && !name.isNullOrEmpty() && order != null) {
                Category(categoryId, name, order)
            } else {
                null
            }
        }
    }
}