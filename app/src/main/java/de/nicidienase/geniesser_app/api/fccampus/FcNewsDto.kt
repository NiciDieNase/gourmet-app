package de.nicidienase.geniesser_app.api.fccampus

import com.google.gson.annotations.SerializedName

data class FcNewsDto (
    @SerializedName("_id")
    val id: String,
    val title: String,
    val notoficationText: String,
    val newsText: String,
    val date: String
)
