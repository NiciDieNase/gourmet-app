package de.nicidienase.geniesser_app.data

import android.os.Parcel
import android.os.Parcelable
import de.nicidienase.geniesser_app.data.api.SpeiseplanKategorieDto

class Categorie(var categoryId: Int, var name: String): Parcelable {

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

    companion object CREATOR : Parcelable.Creator<Categorie> {
        override fun createFromParcel(parcel: Parcel): Categorie {
            return Categorie(parcel)
        }

        override fun newArray(size: Int): Array<Categorie?> {
            return arrayOfNulls(size)
        }

        fun fromSpeiseplanKategorieDto(dto: SpeiseplanKategorieDto): Categorie? {
            val categoryId = dto.gerichtkategorieID
            val name = dto.name
            return if(categoryId != null && !name.isNullOrEmpty()) {
                Categorie(categoryId, name)
            } else {
                null
            }
        }
    }
}