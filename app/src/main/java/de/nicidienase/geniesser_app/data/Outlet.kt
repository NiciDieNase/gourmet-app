package de.nicidienase.geniesser_app.data

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import de.nicidienase.geniesser_app.api.OutletDto

@Keep
@Entity(indices = [
    Index(value = ["outletId"], unique = true),
    Index(value = ["locationId"])
])
data class Outlet(
    var outletId: Long,
    var name: String,
    var order: Long,
    var locationId: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    companion object {
        fun fromDto(dto: OutletDto): Outlet? {
            val name = dto.name
            return if (name == null) {
                null
            } else
                Outlet(
                    dto.id,
                    name,
                    dto.order,
                    dto.standortID
                )
        }
    }
}
