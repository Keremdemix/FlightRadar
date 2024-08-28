package com.keremdemir.flightradar.data.repository

import com.keremdemir.flightradar.data.model.DestinationResponse
import com.keremdemir.flightradar.data.model.FlightResponse
import com.keremdemir.flightradar.data.service.FlightsApi

class FlightsRepositoryImpl(
    private val flightsApi: FlightsApi
) : FlightsRepository {

    override suspend fun getFlights(): FlightResponse? {
        return flightsApi.getFlights()
    }

    override suspend fun getDestinations(): DestinationResponse? {
        return flightsApi.getDestinations()
    }
}