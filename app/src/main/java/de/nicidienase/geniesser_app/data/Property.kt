package de.nicidienase.geniesser_app.data

import android.os.Parcel
import android.os.Parcelable
import de.nicidienase.geniesser_app.data.api.SpeiseplanMerkmalDto

class Property(
    var propertyId: Int,
    var name: String,
    var alternateName: String,
    var abbreviation: String,
    var iconUrl: String,
    var showInMenu: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(propertyId)
        parcel.writeString(name)
        parcel.writeString(alternateName)
        parcel.writeString(abbreviation)
        parcel.writeString(iconUrl)
        parcel.writeByte(if (showInMenu) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Property> {
        override fun createFromParcel(parcel: Parcel): Property {
            return Property(parcel)
        }

        override fun newArray(size: Int): Array<Property?> {
            return arrayOfNulls(size)
        }

        fun fromDto(dto: SpeiseplanMerkmalDto): Property? {
            val propertyId = dto.gerichtmerkmalID
            val name = dto.name
            val nameAlternative = dto.nameAlternative
            val abbreviation = dto.kuerzel
            val logoImage = dto.logoImage
            val showInSpeiseplanOverview = dto.showInSpeiseplanOverview
            return if(
                propertyId != null
                && !name.isNullOrEmpty()
                && !nameAlternative.isNullOrEmpty()
                && !abbreviation.isNullOrEmpty()
                && !logoImage.isNullOrEmpty()
                && showInSpeiseplanOverview != null)
                Property(propertyId,name,nameAlternative,abbreviation,logoImage, showInSpeiseplanOverview)
            else null
        }
    }

}