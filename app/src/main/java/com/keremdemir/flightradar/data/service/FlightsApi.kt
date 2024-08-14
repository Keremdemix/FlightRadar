package com.keremdemir.flightradar.data.service

import com.keremdemir.flightradar.data.model.DestinationResponse
import com.keremdemir.flightradar.data.model.FlightResponse
import retrofit2.http.GET

interface FlightsApi {

    @GET("flights")
    suspend fun getFlights(): FlightResponse?

    @GET("destinations")
    suspend fun getDestinations(): DestinationResponse?
}