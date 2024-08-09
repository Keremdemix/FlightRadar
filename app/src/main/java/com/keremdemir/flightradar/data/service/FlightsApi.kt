package com.keremdemir.flightradar.data.service

import com.keremdemir.flightradar.data.model.DestinationResponse
import com.keremdemir.flightradar.data.model.FlightResponse
import retrofit2.http.GET
import retrofit2.http.Headers

//interface
interface FlightsApi {
    @Headers(
        "App_ID: 722aae00","App_Key: 3a7d9ec3d3657bcd31a14d730f415f33","ResourceVersion:v4"
    )
    @GET("flights")
    suspend fun getFlights(): FlightResponse?

    @Headers(
        "App_ID: 722aae00","App_Key: 3a7d9ec3d3657bcd31a14d730f415f33","ResourceVersion:v4"
    )
    @GET("destinations")
    suspend fun getDestinations(): DestinationResponse?
}