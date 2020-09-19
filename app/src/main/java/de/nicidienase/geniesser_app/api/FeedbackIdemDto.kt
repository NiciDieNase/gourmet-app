package de.nicidienase.geniesser_app.api

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class FeedbackIdemDto(
    val id: Long,
    @SerializedName("benutzerId") val userId: Long,
    @SerializedName("gerichtFeedbackTypeID") val feedbackTypeId: Long,
    val name: String,
    @SerializedName("reihenfolge") val order: Long,
    @SerializedName("timestampLog") val timestamp: String
)
/*
feedbackType
1: numeric (1-6)
3: Text
4: Headline only (no response)
99: Headline for Main-Menu
 */
