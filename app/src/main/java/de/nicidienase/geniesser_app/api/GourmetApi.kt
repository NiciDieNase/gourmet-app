package de.nicidienase.geniesser_app.api

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GourmetApi {

    @GET("$BASE_PATH/entity.standort/")
    suspend fun getLocations(): List<StandortDto>

    @GET("$BASE_PATH/entity.outlet/")
    suspend fun getOutlets(): List<OutletDto>

    @GET("$BASE_PATH/entity.speiseplanadvanced/getdata/{location_id}/1")
    suspend fun getMenu(@Path("location_id") locationId: Long): Response<List<SpeiseplanWrapperDto>?>

    @GET("$BASE_PATH/entity.gerichtkategorie/current/{location_id}")
    suspend fun getMenuCategories(@Path("location_id") locationId: Long): List<SpeiseplanKategorieDto>

    @GET("$BASE_PATH/entity.gerichtmerkmal/current/{location_id}")
    suspend fun getDishProperties(@Path("location_id") locationId: Long): List<SpeiseplanMerkmalDto>

    @GET("$BASE_PATH/entity.zusatzstoffe/current/{location_id}")
    suspend fun getAdditives(@Path("location_id") locationId: Long): List<ZusatzStoffDto>

    @GET("$BASE_PATH/entity.allergene/current/{location_id}")
    suspend fun getAllergens(@Path("location_id") locationId: Long): List<AllergenDto>

    @GET("$BASE_PATH/entity.news/current/{location_id}")
    suspend fun getNews(@Path("location_id") locationId: Long): List<NewsDto>

    @GET("$BASE_PATH/entity.gerichtfeedback/")
    suspend fun getFeedbackCategories(): List<FeedbackIdemDto>

    @POST("$BASE_PATH/entity.gerichtfeedbackmessage/createAll")
    suspend fun sendFeedback(@Body messageItem: FeedbackMessage)

    @GET("$BASE_PATH/entity.appqrzugang")
    suspend fun getQrInfo(): List<QrInfoDto>

    companion object {
        private const val BASE_PATH = "kms-mt-webservices/webresources"

        val instance: GourmetApi by lazy {
            val okhttpClient = OkHttpClientFactory.okHttpClient.newBuilder()
                .addInterceptor { chain: Interceptor.Chain ->
                    val request = chain.request()
                        .newBuilder()
                        .header("Authorization", "Basic d3NfbGl2ZV91c2VyOkF0TXM0SEd5RSFWTjc=")
                        .header("Accept", "application/json")
                        .header("User-Agent", "Mozilla/5.0 (Linux; Android 5.0.2; Android SDK built for x86_64 Build/LSY66K) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/37.0.0.0 Mobile Safari/537.36")
                        .header("X-LANGUAGETYPE", "1")
                        .header("X-Requested-With", "de.konkaapps.mittagstisch.geha")
                        .header("X-API-MT-VERSION", "MTA3.23.0")
                        .build()
                    return@addInterceptor chain.proceed(request)
                }
                .build()

            val gson = GsonBuilder().setLenient().create()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://genussapp-api-mt.konkaapps.de/")
                .client(okhttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            retrofit.create(GourmetApi::class.java)
        }
    }
}
