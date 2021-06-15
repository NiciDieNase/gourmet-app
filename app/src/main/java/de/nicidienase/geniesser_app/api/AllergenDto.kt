package de.nicidienase.geniesser_app.api

import androidx.annotation.Keep

@Keep
data class AllergenDto(
    var allergeneID: Int?,
    var benutzerID: Int?,
    var id: Int?,
    var kuerzel: String?,
    var languageTypeID: Int,
    var logoImage: String?,
    var name: String?,
    var timestampLog: String?
)
