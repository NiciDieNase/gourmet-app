package de.nicidienase.geniesser_app.api

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class FeedbackMessage(
    @SerializedName("gerichtFeedbackMessage") val message: List<FeedbackMessageItem>
)

@Keep
data class FeedbackMessageItem (
    val feedbackId: Long,
    val gerichtFeedbackId: Long,
    val gerichtId: Long,
    val value: Any,
    @SerializedName("zeitstempel") val timestamp: String
)
