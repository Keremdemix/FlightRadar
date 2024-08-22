package com.keremdemir.flightradar.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.keremdemir.flightradar.data.model.Flight
import com.keremdemir.flightradar.data.service.RetrofitInstance
import kotlinx.coroutines.launch

class FlightsViewModel : BaseViewModel() {

    private var _flights = MutableLiveData<List<Flight>>()
    val flights: LiveData<List<Flight>> by ::_flights

    private var pureFlightList: List<Flight>? = null

    init {
        fetchFlights()
    }

    private fun fetchFlights() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getFlights()
                response?.flights?.let { flightList ->

                    flightList.forEach {
//                        dalist.forEach {
//                            isf
//                        }
                    }

                    pureFlightList = flightList
                    _flights.value = flightList
                }
                Log.d("APIResponse", "Response: $response")
            } catch (e: Exception) {
                Log.e("APIError", "Error fetching flights", e)
            }
        }
    }

    fun showAllItems() {
        _flights.value = pureFlightList
    }

    fun showFavoriteItems(favoritesItemIdsList: List<String>) {


        val flightList = pureFlightList
        val filteredFlightsList = arrayListOf<Flight>()
        flightList?.forEach { flight ->
            favoritesItemIdsList.forEach { favoritedId ->
                if (flight.id == favoritedId) {
                    filteredFlightsList.add(flight)
                }
            }
        }

        _flights.value = filteredFlightsList
    }
}


