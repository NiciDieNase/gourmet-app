package de.nicidienase.geniesser_app.api

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class OutletDto(
    var id: Long,
    var name: String?,
    var logoImage: String?,
    var outletImage: String?,
    @SerializedName("reihenfolge") var order: Long,
    var standortID: Long,
    var locationInfo: LocationInfoDto
)
/*
"id": 1,
"name": "HUK Bahnhofsplatz Coburg",
"logoImage": "https://genussapp.konkaapps.de/KMSLiveRessources/outlet/Logo.png",
"outletImage": null,
"reihenfolge": 1,
"outletRGBColor": null,
"oeffnungszeitenRichtext": null,
"moZeit1": "11:30 - 13:30 Uhr",
"moZeit2": null,
"diZeit1": "11:30 - 13:30 Uhr",
"diZeit2": null,
"miZeit1": "11:30 - 13:30 Uhr",
"miZeit2": null,
"doZeit1": "11:30 - 13:30 Uhr",
"doZeit2": null,
"frZeit1": "11:30 - 13:30 Uhr",
"frZeit2": null,
"saZeit1": "Geschlossen",
"saZeit2": null,
"soZeit1": "Geschlossen",
"soZeit2": null,
"adresseID": 1,
"kontaktID": 1,
"standortID": 3300,
"timestampLog": "2020-09-07T10:41:12",
"benutzerID": 196,
"outletHasMapposition": null,
"homepage": "www.die-frischemacher.de",
"addressInfo": {
    "id": 1,
    "additionalInfo": null,
    "street": "Bahnhofsplatz",
    "city": "Coburg",
    "postalCode": "D-96450",
    "countryName": "Deutschland",
    "countryCode": "DE"
},
"contactInfo": [
    {
        "id": 1,
        "contactPerson": "Tino Schmidt (F&B Manager)",
        "phone": "0151 15045080",
        "fax": null,
        "email": "Tino.schmidt@die-frischemacher.de",
        "role": null
    }
],
"positionInfo": null,
"linkInfo": [],
"assignedDishIds": [],
"assignedDishes": [],
"availableDishCategoryIds": [],
"availableDishCategories": [],
"availablePickupTimes": [],
"availableHolidays": [],
"availableOrderInfos": [],
"locationInfo": {
    "id": 3300,
    "name": "HUK Coburg"
}
*/
