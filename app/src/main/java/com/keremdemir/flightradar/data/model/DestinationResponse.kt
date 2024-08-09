package com.keremdemir.flightradar.data.model

data class DestinationResponse(
    val destinations: List<Destination>
)

data class Destination(
    val country: String,
    val iata: String,
    val publicName: PublicName,
    val city: String?,
)

data class PublicName(
    val dutch: String,
    val english: String,
)




