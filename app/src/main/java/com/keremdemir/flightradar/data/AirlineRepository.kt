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
    )

    fun getAirlineDataByICAO(icaoCode: String): AirlineData? {
        return airlineDataMap[icaoCode]
    }
}