package de.nicidienase.geniesser_app.api

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class FeedbackMessage(
    @SerializedName("gerichtFeedbackMessage") val message: List<FeedbackMessageItem>
)

@Keep
data class FeedbackMessageItem(
    val feedbackId: Long,
    val gerichtFeedbackId: Long,
    val gerichtId: Long,
    val value: Any,
    @SerializedName("zeitstempel") val timestamp: String
)

/*
https://genussapp-api-mt.konkaapps.de/kms-mt-webservices/webresources/entity.gerichtfeedbackmessage/createAll
{
    "gerichtFeedbackMessage": [
        {
            "feedbackID": 32,
            "gerichtFeedbackID": 1,
            "gerichtID": 307183,
            "value": 6,
            "zeitstempel": "2020-09-22T03:02:59"
        },
        {
            "feedbackID": 32,
            "gerichtFeedbackID": 2,
            "gerichtID": 307183,
            "value": 6,
            "zeitstempel": "2020-09-22T03:02:59"
        },
        {
            "feedbackID": 32,
            "gerichtFeedbackID": 3,
            "gerichtID": 307183,
            "value": 5,
            "zeitstempel": "2020-09-22T03:02:59"
        },
        {
            "feedbackID": 32,
            "gerichtFeedbackID": 4,
            "gerichtID": 307183,
            "value": 5,
            "zeitstempel": "2020-09-22T03:02:59"
        },
        {
            "feedbackID": 32,
            "gerichtFeedbackID": 5,
            "gerichtID": 307183,
            "value": 6,
            "zeitstempel": "2020-09-22T03:02:59"
        },
        {
            "feedbackID": 32,
            "gerichtFeedbackID": 7,
            "gerichtID": 307183,
            "value": "TOP",
            "zeitstempel": "2020-09-22T03:02:59"
        },
        {
            "feedbackID": 32,
            "gerichtFeedbackID": 6,
            "gerichtID": 307183,
            "value": 4,
            "zeitstempel": "2020-09-22T03:02:59"
        }
    ]
}
 */