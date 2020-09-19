package de.nicidienase.geniesser_app.api.fccampus

import com.google.gson.GsonBuilder
import de.nicidienase.geniesser_app.api.OkHttpClientFactory
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface FcCampusApi {

    @GET("$BASE_PATH/dayMenus/{from}/{to}")
    suspend fun getMenus(
        @Path("from") fromDate: String,
        @Path("to") toDate: String
    ): MenusWrapperDto

    @GET("$BASE_PATH/mealTimes/{week}")
    suspend fun getMealTimes(
        @Path("week") week: Int
    ): MealTimesWrapperDto

    companion object {
        private const val BASE_PATH = "restaurants/5f17207ae9b5b375023b529b"

        val instance: FcCampusApi by lazy {

            val okhttpClient = OkHttpClientFactory.okHttpClient.newBuilder()
                .addInterceptor { chain: Interceptor.Chain ->
                    val request = chain.request()
                        .newBuilder()
                        .header("Accept", "application/json")
                        .header("User-Agent", "Dart/2.8 (dart:io)")
                        .build()
                    return@addInterceptor chain.proceed(request)
                }
                .build()

            val gson = GsonBuilder().setLenient().create()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://fccampus.azurewebsites.net/")
                .client(okhttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            retrofit.create(FcCampusApi::class.java)
        }
    }
}