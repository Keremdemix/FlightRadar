package com.keremdemir.flightradar.ui.flightdetails

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.keremdemir.flightradar.ui.component.FRTop
import com.keremdemir.flightradar.ui.component.FlightDetailsCard
import com.keremdemir.flightradar.ui.component.LoadingView
import com.keremdemir.flightradar.ui.component.TopBarConfig
import com.keremdemir.flightradar.ui.viewmodel.FlightsViewModel


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightDetailsScreen(onClickedFlightID: String, onBackButtonClicked: () -> Unit) {
    val flightsViewModel: FlightsViewModel = viewModel()
    val flights = flightsViewModel.flights.observeAsState().value?.flights

    if (!flights.isNullOrEmpty()) {
        Column {
            flights.forEach { flight ->
                flight.baggageClaim?.let {
                    if (flight.id == onClickedFlightID) {
                        FRTop(
                            config = TopBarConfig(
                                isFavIconShown = true,
                                isHomeScreen = false,
                                title = "${flight.flightName}: ${flight.prefixICAO}-${flight.route.destinations[0]}",
                                isBackButtonShown = true
                            ), onBackButtonClicked = onBackButtonClicked
                        )
                        FlightDetailsCard(
                            flightItem = flight
                        )
                    }
                }
            }
        }
    } else LoadingView()
}