package de.nicidienase.geniesser_app.data

import android.os.Parcel
import android.os.Parcelable
import de.nicidienase.geniesser_app.api.SpeiseplanGerichtDto

class Dish(
    var name: String,
    var date: String,
    var price: Float,
    var category: Int,
    var allergens: List<Int>? = null,
    var additives: List<Int>? = null,
    var properties: List<Int>? = null
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readFloat(),
        parcel.readInt()
    ) {
        allergens = mutableListOf<Int>().apply {
            parcel.readList(this as List<*>, Int::class.java.classLoader)
        }
        additives = mutableListOf<Int>().apply {
            parcel.readList(this as List<*>, Int::class.java.classLoader)
        }
        properties = mutableListOf<Int>().apply {
            parcel.readList(this as List<*>, Int::class.java.classLoader)
        }
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(date)
        parcel.writeFloat(price)
        parcel.writeInt(category)
        parcel.writeList(allergens)
        parcel.writeList(additives)
        parcel.writeList(properties)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Dish> {
        override fun createFromParcel(parcel: Parcel): Dish {
            return Dish(parcel)
        }

        override fun newArray(size: Int): Array<Dish?> {
            return arrayOfNulls(size)
        }

        fun fromGerichtDto(gerichtDto: SpeiseplanGerichtDto): Dish? {
            val name = gerichtDto.speiseplanAdvancedGericht.gerichtname
            val date = gerichtDto.speiseplanAdvancedGericht.datum
            val price = gerichtDto.zusatzinformationen.mitarbeiterpreisDecimal2
            val allergens: List<Int>?
                    = gerichtDto.allergeneIds?.split(",")?.map { it.toInt() }
            val dishProperties: List<Int>?
                    = gerichtDto.gerichtmerkmaleIds?.split(",")?.map { it.toInt() }
            val dishCategoryId = gerichtDto.speiseplanAdvancedGericht.gerichtkategorieID
            val additivesIds: List<Int>?
                    = gerichtDto.zusatzstoffeIds?.split(",")?.map { it.toInt() }
            return if( !name.isNullOrBlank()
                && !date.isNullOrBlank()
                && price != null
                && dishCategoryId != null) {
                Dish(name, date, price, dishCategoryId, allergens, additivesIds, dishProperties)
            } else {
                null
            }
        }
    }
}