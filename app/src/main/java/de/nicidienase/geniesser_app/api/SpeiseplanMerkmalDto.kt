package de.nicidienase.geniesser_app.api

import androidx.annotation.Keep

@Keep
data class SpeiseplanMerkmalDto(
    var benutzerID: Int?,
    var gerichtmerkmalID: Int?,
    var id: Int?,
    var kuerzel: String?,
    var languageTypeID: Int?,
    var logoImage: String?,
    var name: String?,
    var nameAlternative: String?,
    var reihenfolgeInApp: Int?,
    var showInSpeiseplanOverview: Boolean?,
    var showNotInFilter: Boolean?,
    var timestampLog: String?
)
