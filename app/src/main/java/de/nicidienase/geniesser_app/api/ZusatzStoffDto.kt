package de.nicidienase.geniesser_app.api

import androidx.annotation.Keep

@Keep
data class ZusatzStoffDto(
    var benutzerID: Int?,
    var id: Int?,
    var kuerzel: String?,
    var languageTypeID: Int?,
    var name: String?,
    var timestampLog: String?,
    var zusatzstoffeID: Int?
)
