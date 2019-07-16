package de.nicidienase.geniesser_app.api

data class SpeiseplanKategorieDto(
    var benutzerID: Int?,
    var gerichtkategorieID: Int?,
    var id: Int?,
    var languageTypeID: Int?,
    var name: String?,
    var reihenfolgeInApp: Int?,
    var timestampLog: String?
)