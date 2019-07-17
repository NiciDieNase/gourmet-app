package de.nicidienase.geniesser_app.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import de.nicidienase.geniesser_app.api.SpeiseplanGerichtDto

@Entity
data class Dish(
    var dishId: Int,
    var name: String,
    var date: String,
    var price: Float,
    var category: Int,
    var allergenIds: List<Int>? = null,
    var additiveIds: List<Int>? = null,
    var propertyIds: List<Int>? = null
): Parcelable {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    @Ignore
    var allergens: List<Allergen>? = null
    @Ignore
    var additives: List<Additive>? = null
    @Ignore
    var properties: List<Property>? = null


    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readFloat(),
        parcel.readInt()
    ) {
        allergenIds = mutableListOf<Int>().apply {
            parcel.readList(this as List<*>, Int::class.java.classLoader)
        }
        additiveIds = mutableListOf<Int>().apply {
            parcel.readList(this as List<*>, Int::class.java.classLoader)
        }
        propertyIds = mutableListOf<Int>().apply {
            parcel.readList(this as List<*>, Int::class.java.classLoader)
        }
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(dishId)
        parcel.writeString(name)
        parcel.writeString(date)
        parcel.writeFloat(price)
        parcel.writeInt(category)
        parcel.writeList(allergenIds)
        parcel.writeList(additiveIds)
        parcel.writeList(propertyIds)
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
            val dishId = gerichtDto.speiseplanAdvancedGericht.id
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
                && dishCategoryId != null
                && dishId != null) {
                Dish(dishId, name, date, price, dishCategoryId, allergens, additivesIds, dishProperties)
            } else {
                null
            }
        }
    }
}