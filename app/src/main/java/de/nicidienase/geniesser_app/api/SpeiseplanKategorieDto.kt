package de.nicidienase.geniesser_app.api

import androidx.annotation.Keep

@Keep
data class SpeiseplanKategorieDto(
    var benutzerID: Int?,
    var gerichtkategorieID: Int?,
    var id: Int?,
    var languageTypeID: Int?,
    var name: String?,
    var reihenfolgeInApp: Int?,
    var timestampLog: String?
)