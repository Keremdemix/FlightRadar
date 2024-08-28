package com.keremdemir.flightradar.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.keremdemir.flightradar.data.model.DestinationResponse
import com.keremdemir.flightradar.data.repository.FlightsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DestinationViewModel @Inject constructor(
    private val flightsRepository: FlightsRepository
) : BaseViewModel() {

    private var _destinations = MutableLiveData<DestinationResponse>()
    val destinations: LiveData<DestinationResponse> by ::_destinations

    init {
        fetchDestinations()
    }

    private fun fetchDestinations() {
        viewModelScope.launch {
            try {
                val response = flightsRepository.getDestinations()
                _destinations.value = response
                Log.d("APIResponse", "Response: $response")
            } catch (e: Exception) {
                Log.e("APIError", "Error fetching destinations", e)
            }
        }
    }
}
