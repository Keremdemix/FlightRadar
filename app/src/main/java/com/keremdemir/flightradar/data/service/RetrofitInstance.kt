package com.keremdemir.flightradar.data.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//singleton
object RetrofitInstance {
    private const val BASE_URL="https://api.schiphol.nl/public-flights/"
    val api: FlightsApi by lazy {
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(FlightsApi::class.java)
    }
}



