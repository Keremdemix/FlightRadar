package com.keremdemir.flightradar.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keremdemir.flightradar.data.model.FlightResponse
import com.keremdemir.flightradar.data.service.RetrofitInstance
import kotlinx.coroutines.launch

class FlightsViewModel : ViewModel() {

    private var _flights =  MutableLiveData<FlightResponse>()
    val flights: LiveData<FlightResponse> by ::_flights

    init {
        fetchFlights()
    }

     private fun fetchFlights() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getFlights()
                _flights.value = response
                Log.d("APIResponse", "Response: $response")
            } catch (e: Exception) {
                Log.e("APIError", "Error fetching flights", e)
            }
        }
    }
}
