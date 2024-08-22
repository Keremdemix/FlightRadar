package com.keremdemir.flightradar.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.keremdemir.flightradar.data.model.DestinationResponse
import com.keremdemir.flightradar.data.service.RetrofitInstance
import kotlinx.coroutines.launch

class DestinationViewModel : BaseViewModel() {

    private var _destinations =  MutableLiveData<DestinationResponse>()
    val destinations: LiveData<DestinationResponse> by ::_destinations

    init {
        fetchDestinations()
    }

    private fun fetchDestinations() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getDestinations()
                _destinations.value = response
                Log.d("APIResponse", "Response: $response")
            } catch (e: Exception) {
                Log.e("APIError", "Error fetching flights", e)
            }
        }
    }
}
