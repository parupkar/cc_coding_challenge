package com.cc.codingchallenge.data

import com.cc.codingchallenge.data.models.MarketsResponse
import com.cc.codingchallenge.data.models.StocksResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MyApi {

    @Headers("x-rapidapi-key: d3fe48a491msh36d3be5598466ecp1a30bcjsn6517e0dcd999", "x-rapidapi-host: yh-finance.p.rapidapi.com")
    @GET("market/v2/get-summary?region=US")
    suspend fun getMarketsData(): MarketsResponse

    @Headers("x-rapidapi-key: d3fe48a491msh36d3be5598466ecp1a30bcjsn6517e0dcd999", "x-rapidapi-host: yh-finance.p.rapidapi.com")
    @GET("stock/v2/get-summary?region=US")
    suspend fun getStocksData(@Query("symbol") symbol: String?): StocksResponse

    companion object {

        private const val BASE_URL = "https://yh-finance.p.rapidapi.com/"

        operator fun invoke(): MyApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().also { client ->
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)
    }
}

