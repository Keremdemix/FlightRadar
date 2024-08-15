package com.keremdemir.flightradar.data.model

data class FlightResponse(
    val flights: List<Flight>
)

data class Flight(
    val lastUpdatedAt: String,
    val actualLandingTime: String?,
    val aircraftType: AircraftType,
    val baggageClaim: BaggageClaim?,
    val estimatedLandingTime: String?,
    val expectedTimeOnBelt: String?,
    val flightDirection: String,
    val flightName: String,
    val flightNumber: Int,
    val id: String,
    val isOperationalFlight: Boolean,
    val mainFlight: String,
    val prefixIATA: String,
    val prefixICAO: String,
    val airlineCode: Int,
    val publicFlightState: PublicFlightState,
    val route: Route,
    val scheduleDateTime: String,
    val scheduleDate: String,
    val scheduleTime: String,
    val serviceType: String,
    val terminal: Int,
    val schemaVersion: String,
    val codeshares: Codeshares? = null
)

data class AircraftType(
    val iataMain: String, val iataSub: String
)

data class BaggageClaim(
    val belts: List<String>
)

data class PublicFlightState(
    val flightStates: List<String>
)

data class Route(
    val destinations: List<String>, val eu: String, val visa: Boolean
)

data class Codeshares(
    val codeshares: List<String>
)