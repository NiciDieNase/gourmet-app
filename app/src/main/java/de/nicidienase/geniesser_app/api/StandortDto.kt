package de.nicidienase.geniesser_app.api

import androidx.annotation.Keep

@Keep
data class StandortDto(
    var benutzerId: Int?,
    var id: Long?,
    var logoImage: String?,
    var name: String?,
    var reihenfolge: Int?,
    var timestampLog: String?
)
