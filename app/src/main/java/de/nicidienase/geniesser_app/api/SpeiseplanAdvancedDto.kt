package de.nicidienase.geniesser_app.api

import androidx.annotation.Keep

@Keep
data class SpeiseplanAdvancedDto(
    var exportInactiveContent: Boolean?,
    var gueltigBis: String?,
    var gueltigTaeglich: Boolean?,
    var gueltigVon: String?,
    var id: Int?,
    var outletID: Int?,
    var reihenfolgeInApp: Int?,
    var showWeekend: Boolean?,
    var timestampLog: String?,
    var titel: String?
)
