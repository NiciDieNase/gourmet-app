package de.nicidienase.geniesser_app.api

import okhttp3.OkHttpClient

object OkHttpClientFactory {
    val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }
}
