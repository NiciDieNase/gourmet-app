package de.nicidienase.geniesser_app.data

class SpeiseplanGerichtDto (
    var allergeneIds: String?,
    var gerichtmerkmaleIds: String?,
    var speiseplanAdvancedGericht: AdvancedGerichtDto,
    var zusatzinformationen: ZusatzinformationenDto,
    var zusatzstoffeIds: String?
)

class ZusatzinformationenDto (
    var allowFeedback: Boolean?,
    var id: Int?,
    var mitarbeiterpreisDecimal2: Float?
)

class AdvancedGerichtDto (
    var aktiv: Boolean?,
    var benutzerID: Int?,
    var datum: String?,
    var gerichtkategorieID: Int?,
    var gerichtname: String?,
    var id: Int?,
    var reihenfolgeInGerichtkategorie: Int?,
    var speiseplanAdvancedID: Int?,
    var timestampLog: String?,
    var zusatzinformationenID: String?
)