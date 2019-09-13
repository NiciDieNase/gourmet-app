package de.nicidienase.geniesser_app.api

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface GourmetApi {

    @GET("KMSLiveWebservices/webresources/entity.standort/")
    suspend fun getLocations(): List<StandortDto>

    @GET("KMSLiveWebservices/webresources/entity.speiseplanadvanced/getdata/{location_id}/1")
    suspend fun getMenu(@Path("location_id") locationId: Int): List<SpeiseplanWrapperDto>?

    @GET("KMSLiveWebservices/webresources/entity.gerichtkategorie/current/{location_id}")
    suspend fun getMenuCategories(@Path("location_id") locationId: Int): List<SpeiseplanKategorieDto>

    @GET("KMSLiveWebservices/webresources/entity.gerichtmerkmal/current/{location_id}")
    suspend fun getDishProperties(@Path("location_id") locationId: Int): List<SpeiseplanMerkmalDto>

    @GET("KMSLiveWebservices/webresources/entity.zusatzstoffe/current/{location_id}")
    suspend fun getAdditives(@Path("location_id") locationId: Int): List<ZusatzStoffDto>

    @GET("KMSLiveWebservices/webresources/entity.allergene/current/{location_id}")
    suspend fun getAllergens(@Path("location_id") locationId: Int): List<AllergenDto>

    @GET("KMSLiveWebservices/webresources/entity.news/current/{location_id}")
    suspend fun getNews(@Path("location_id") locationId: Int): List<NewsDto>
}

fun buildMenuApi(): GourmetApi {
    val okhttpClient = OkHttpClient.Builder()
        .addInterceptor { chain: Interceptor.Chain ->
            val request = chain.request()
                .newBuilder()
                .header("Authorization", "Basic d3NfbGl2ZV91c2VyOkF0TXM0SEd5RSFWTjc=")
                .header("Accept", "application/json")
                .header("User-Agent", "Mozilla/5.0 (Linux; Android 4.4.4; Nexus S Build/KTU84Q) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/33.0.0.0 Mobile Safari/537.36")
                .header("X-LANGUAGETYPE", "1")
                .header("X-Requested-With", "de.konkaapps.mittagstisch.geha")
                .build()
            return@addInterceptor chain.proceed(request)
        }
        .build()

    val gson = GsonBuilder().setLenient().create()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://genussapp.konkaapps.de/")
        .client(okhttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    return retrofit.create(GourmetApi::class.java)
}