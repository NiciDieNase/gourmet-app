package de.nicidienase.geniesser_app.api

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class NewsDto(
    val id: Long,
    @SerializedName("titel") val title: String?,
    @SerializedName("inhaltRichtext") val content: String?,
    @SerializedName("newsImage") val newsImageUrl: String?,
    @SerializedName("datum") val date: String?,
    @SerializedName("benutzerID") val userId: Long?,
    @SerializedName("interneNews") val internal: Boolean?,
    @SerializedName("timestampLog") val timestampLog: String?,
    @SerializedName("locationInfo") val locationInfo: List<LocationInfoDto>
)

@Keep
class LocationInfoDto(val id: Long, val name: String)
