package de.nicidienase.geniesser_app.data

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import de.nicidienase.geniesser_app.api.AllergenDto

@Keep
@Entity(indices = [Index(value = ["allergenId"], unique = true)])
class Allergen(
    var allergenId: Int,
    var name: String,
    var logoUrl: String,
    var abbreviation: String
) : Parcelable {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(allergenId)
        parcel.writeString(name)
        parcel.writeString(logoUrl)
        parcel.writeString(abbreviation)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Allergen> {
        override fun createFromParcel(parcel: Parcel): Allergen {
            return Allergen(parcel)
        }

        override fun newArray(size: Int): Array<Allergen?> {
            return arrayOfNulls(size)
        }

        fun fromAllergenDto(dto: AllergenDto): Allergen? {
            val allergeneID = dto.allergeneID
            val name = dto.name
            val logoImage = dto.logoImage
            val abbreviation = dto.kuerzel
            return if (allergeneID != null &&
                !name.isNullOrEmpty() &&
                !logoImage.isNullOrEmpty() &&
                !abbreviation.isNullOrEmpty()
            ) Allergen(allergeneID, name, logoImage, abbreviation)
            else null
        }
    }
}
