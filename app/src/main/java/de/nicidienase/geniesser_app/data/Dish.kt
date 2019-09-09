package de.nicidienase.geniesser_app.data

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import de.nicidienase.geniesser_app.api.SpeiseplanAdvancedDto
import de.nicidienase.geniesser_app.api.SpeiseplanGerichtDto
import de.nicidienase.geniesser_app.api.SpeiseplanKategorieDto
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Keep
@Entity(indices = [Index(value = ["dishId"], unique = true)])
data class Dish(
    val locationId: Int,
    val outletId: Int,
    var dishId: Int,
    var name: String,
    var date: Date,
    var price: Int,
    @Embedded
    var category: Category?,
    var allergenIds: List<Int>? = null,
    var additiveIds: List<Int>? = null,
    var propertyIds: List<Int>? = null
) : Parcelable {

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
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString() ?: "",
        Date(parcel.readLong()),
        parcel.readInt(),
        parcel.readParcelable<Category>(Category::class.java.classLoader)
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
        parcel.writeInt(locationId)
        parcel.writeInt(outletId)
        parcel.writeInt(dishId)
        parcel.writeString(name)
        parcel.writeLong(date.time)
        parcel.writeInt(price)
        parcel.writeParcelable(category, 0)
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

        fun fromGerichtDto(
            gerichtDto: SpeiseplanGerichtDto,
            locationId: Int,
            planInfo: SpeiseplanAdvancedDto,
            kategorieDto: List<SpeiseplanKategorieDto>
        ): Dish? {
            val dishId = gerichtDto.speiseplanAdvancedGericht.id
            val name = gerichtDto.speiseplanAdvancedGericht.gerichtname
            val date = gerichtDto.speiseplanAdvancedGericht.datum?.let {
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.GERMANY).parse(it)
            }

            val price = gerichtDto.zusatzinformationen.mitarbeiterpreisDecimal2
            val allergens: List<Int>? = gerichtDto.allergeneIds?.split(",")?.map { it.toInt() }
            val dishProperties: List<Int>? = gerichtDto.gerichtmerkmaleIds?.split(",")?.map { it.toInt() }
            val dishCategory =
                kategorieDto.find { it.gerichtkategorieID == gerichtDto.speiseplanAdvancedGericht.gerichtkategorieID }
            val additivesIds: List<Int>? = gerichtDto.zusatzstoffeIds?.split(",")?.map { it.toInt() }
            val outletId = planInfo.outletID
            return if (!name.isNullOrBlank() &&
                date != null &&
                price != null &&
                dishCategory != null &&
                dishId != null &&
                outletId != null
            ) {
                Dish(
                    locationId,
                    outletId,
                    dishId,
                    name,
                    date,
                    (price * 100).toInt(),
                    Category.fromDto(dishCategory),
                    allergens,
                    additivesIds,
                    dishProperties
                )
            } else {
                null
            }
        }
    }
}