package com.keremdemir.flightradar.data.repository

import com.keremdemir.flightradar.data.model.DestinationResponse
import com.keremdemir.flightradar.data.model.FlightResponse

interface FlightsRepository {
    suspend fun getFlights(): FlightResponse?
    suspend fun getDestinations(): DestinationResponse?
}
