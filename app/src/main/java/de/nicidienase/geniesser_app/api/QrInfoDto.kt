package de.nicidienase.geniesser_app.api

import androidx.annotation.Keep

@Keep
data class QrInfoDto(
    var benutzerID: Long,
    var gueltigBis: String,
    var gueltigVon: String,
    var id: Long,
    var name: String,
    var qrCodeContent: String,
    var qrCodeImage: String,
    var timestampLog: String
)
