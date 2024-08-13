package com.keremdemir.flightradar.data.model

data class FlightResponse(
    val flights: List<Flight>
)

data class Flight(
    val lastUpdatedAt: String,
    val terminal: Int,
    val scheduleTime: String,
    val flightName: String,
    val prefixICAO: String,
    val route: Route,
)

data class Route(
    val destinations: List<String>,
    val eu: String,
    val visa: Boolean,
)

