package de.nicidienase.geniesser_app.api

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class NewsDto(
    val id: Long,
    @SerializedName("benutzerID") val userId: Long?,
    @SerializedName("datum") val date: String?,
    @SerializedName("inhaltRichtext") val content: String?,
    @SerializedName("interneNews") val internal: Boolean?,
    @SerializedName("newsImage") val newsImageUrl: String?,
    @SerializedName("timestampLog") val timestampLog: String?,
    @SerializedName("titel") val title: String?,
    @SerializedName("locationInfo") val locationInfo: List<LocationInfoDto>
)

@Keep
class LocationInfoDto( val id: Long, val name: String )
