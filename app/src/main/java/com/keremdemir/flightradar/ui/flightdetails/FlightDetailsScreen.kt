package com.keremdemir.flightradar.ui.flightdetails

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.keremdemir.flightradar.data.AirlineRepository
import com.keremdemir.flightradar.ui.component.FRTop
import com.keremdemir.flightradar.ui.component.FlightDetailsCard
import com.keremdemir.flightradar.ui.component.TopBarConfig
import com.keremdemir.flightradar.ui.viewmodel.FlightsViewModel
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter.ISO_LOCAL_TIME
import java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightDetailsScreen(clickedFlightID:String) {
    val flightsViewModel: FlightsViewModel = viewModel()
    val flights = flightsViewModel.flights.observeAsState().value?.flights

    if (flights != null) {
        Column {
            flights.forEach { flight ->

                val scheduleTime = LocalTime.parse(
                    flight.scheduleTime, ISO_LOCAL_TIME
                )
                val parsedLandingDateTime = OffsetDateTime.parse(
                    flight.estimatedLandingTime, ISO_OFFSET_DATE_TIME
                )
                val parsedActualDateTime = OffsetDateTime.parse(
                    flight.actualLandingTime, ISO_OFFSET_DATE_TIME
                )
                val airlineData = AirlineRepository.getAirlineDataByICAO(flight.prefixICAO)
                val duration = java.time.Duration.between(
                    scheduleTime, parsedLandingDateTime.toLocalTime()
                )
                flight.baggageClaim?.let {
                    if (flight.id == clickedFlightID) {
                        FRTop(
                            config = TopBarConfig(
                                isFavIconShown = true, isHomeScreen = false,
                                title = "${flight.flightName}: ${flight.prefixICAO}-${flight.route.destinations[0]}",
                                isBackButtonShown = true
                            )
                        )
                        FlightDetailsCard(
                            prefixICAO = flight.prefixICAO,
                            flightName = flight.flightName,
                            destination = flight.route.destinations[0],
                            modifier = Modifier,
                            airlineCompany = airlineData!!.name,
                            logo = painterResource(id = airlineData.logoResId),
                            scheduleDate = flight.scheduleDate,
                            scheduleTime = flight.scheduleTime,
                            estimatedLandingDate = parsedLandingDateTime.toLocalDate().toString(),
                            estimatedLandingTime = parsedLandingDateTime.toLocalTime().toString(),
                            duration = "${duration.toHours()}h${duration.toMinutes() - 60 * duration.toHours()}m",
                            actualLandingTime = parsedActualDateTime.toLocalTime().toString(),
                            actualLandingDate = parsedActualDateTime.toLocalDate().toString(),
                            prefixIATA = flight.prefixIATA,
                            baggageClaim = it.belts.joinToString(separator = ", "),
                            flightDirection = flight.flightDirection,
                            flightState = flight.publicFlightState.flightStates.joinToString(
                                separator = ", "
                            ),
                            gate = flight.id
                        )
                    }
                }
            }
        }
    }
}