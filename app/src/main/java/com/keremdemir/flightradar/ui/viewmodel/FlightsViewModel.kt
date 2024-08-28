package com.keremdemir.flightradar.ui.viewmodel

import DataStorePreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keremdemir.flightradar.data.model.Flight
import com.keremdemir.flightradar.data.repository.FlightsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlightsViewModel @Inject constructor(
    private val flightsRepository: FlightsRepository
) : ViewModel() {

    private var _flights = MutableLiveData<List<Flight>>()
    val flights: LiveData<List<Flight>> by ::_flights
    private val dataStorePreferences: DataStorePreferences?=null

    private var pureFlightList: List<Flight>? = null

    init {
        fetchFlights()
    }

    private fun fetchFlights() {
        viewModelScope.launch {
            try {
                val response = flightsRepository.getFlights()
                response?.flights?.let { flightList ->
                    dataStorePreferences?.getFlightIDList()?.forEach { favoriteId ->
                        flightList.forEach {
                            if (it.id == favoriteId)
                                it.isFavorite = true
                        }
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


