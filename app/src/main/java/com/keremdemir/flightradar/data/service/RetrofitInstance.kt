package com.keremdemir.flightradar.data.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//singleton
object RetrofitInstance {
    private const val BASE_URL="https://api.schiphol.nl/public-flights/"
    val api: FlightsApi by lazy {
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        retrofit.create(FlightsApi::class.java)
    }
}

private val okHttpClient: OkHttpClient = OkHttpClient.Builder().apply {
    addInterceptor(
        Interceptor { chain ->
            val builder = chain.request().newBuilder()
            builder.header("App_ID", "722aae00")
            builder.header("App_Key", "3a7d9ec3d3657bcd31a14d730f415f33")
            builder.header("ResourceVersion", "v4")
            return@Interceptor chain.proceed(builder.build())
        }
    )}.build()



