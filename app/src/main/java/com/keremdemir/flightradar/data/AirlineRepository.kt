package com.keremdemir.flightradar.data

import com.keremdemir.flightradar.R
import com.keremdemir.flightradar.data.model.AirlineData

object AirlineRepository {

    private val airlineDataMap = mapOf(
        "TRA" to AirlineData("Transavia", R.drawable.transavia_logo),
        "KLM" to AirlineData("KLM Royal Dutch Airlines", R.drawable.klm_logo),
        "EJU" to AirlineData("easyJet Europe", R.drawable.easyjet_logo),
        "TFL" to AirlineData("TUI", R.drawable.tui),
        "DAL" to AirlineData("Delta Air Lines", R.drawable.dal),
        "SIA" to AirlineData("Singapore Airlines", R.drawable.singapore_airlines),
        "MPH" to AirlineData("Mahan Air", R.drawable.mahan_air_logo),
        "CND" to AirlineData("Air China", R.drawable.air_china),
        "AFR" to AirlineData("Air France", R.drawable.air_france),
    )

    fun getAirlineDataByICAO(icaoCode: String): AirlineData? {
        return airlineDataMap[icaoCode]
    }
}