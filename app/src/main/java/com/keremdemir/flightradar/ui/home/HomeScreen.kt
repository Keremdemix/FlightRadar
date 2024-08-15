package com.keremdemir.flightradar.ui.home


import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.keremdemir.flightradar.R
import com.keremdemir.flightradar.data.AirlineRepository
import com.keremdemir.flightradar.ui.component.DestinationCard
import com.keremdemir.flightradar.ui.component.FRSearchBar
import com.keremdemir.flightradar.ui.component.FlightCard
import com.keremdemir.flightradar.ui.component.LoadingView
import com.keremdemir.flightradar.ui.component.NoResultView
import com.keremdemir.flightradar.ui.component.Top
import com.keremdemir.flightradar.ui.viewmodel.DestinationViewModel
import com.keremdemir.flightradar.ui.viewmodel.FlightsViewModel
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Timer
import kotlin.concurrent.schedule

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("SuspiciousIndentation")
@Composable
fun HomeScreen(onFlightButtonClick: () -> Unit, onFavouriteButtonClick: () -> Unit) {
    val flightsViewModel: FlightsViewModel = viewModel()
    val destinationViewModel: DestinationViewModel = viewModel()
    val destinations = destinationViewModel.destinations.observeAsState().value?.destinations
    val flights = flightsViewModel.flights.observeAsState().value?.flights
    var filteredFlights by remember { mutableStateOf(flights) }
    var timer: Timer = Timer()

    Box {
        Column(
            modifier = Modifier
                .padding(bottom = 70.dp)
                .fillMaxSize()
                .background(Color.White)
        ) {
            Top()
            if (filteredFlights == null) {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Row(Modifier.padding(top = 50.dp, start = 20.dp)) {
                        Text(
                            text = "Top Flights",
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                        )
                        Image(
                            painter = painterResource(id = R.drawable.fly),
                            contentDescription = "fly",
                            modifier = Modifier
                                .padding(start = 5.dp)
                                .size(27.dp)
                                .align(Alignment.CenterVertically)
                        )
                    }
                    if (flights != null) {
                        Column {
                            flights.take(3).forEach { flight ->
                                val scheduleTime = LocalTime.parse(
                                    flight.scheduleTime, DateTimeFormatter.ISO_LOCAL_TIME
                                )
                                val parsedDateTime = OffsetDateTime.parse(
                                    flight.estimatedLandingTime,
                                    DateTimeFormatter.ISO_OFFSET_DATE_TIME
                                )
                                val airlineData =
                                    AirlineRepository.getAirlineDataByICAO(flight.prefixICAO)
                                val duration = java.time.Duration.between(
                                    parsedDateTime.toLocalTime(), scheduleTime
                                )
                                FlightCard(
                                    prefixICAO = flight.prefixICAO,
                                    flightName = flight.flightName,
                                    destination = flight.route.destinations[0],
                                    modifier = Modifier,
                                    airlineCompany = airlineData!!.name,
                                    logo = painterResource(id = airlineData.logoResId),
                                    scheduleDate = flight.scheduleDate,
                                    scheduleTime = flight.scheduleTime,
                                    estimatedLandingDate = parsedDateTime.toLocalDate().toString(),
                                    estimatedLandingTime = parsedDateTime.toLocalTime().toString(),
                                    duration = "${duration.toHours()}h${duration.toMinutes()}m"
                                )
                            }
                        }
                    } else {
                        LoadingView()
                    }
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextButton(onClick = onFlightButtonClick) {
                            Text(text = stringResource(id = R.string.see_more_flights))
                        }
                        TextButton(onClick = onFavouriteButtonClick) {
                            Text(text = stringResource(id = R.string.see_fav_flights))
                        }
                    }
                    Text(
                        text = stringResource(id = R.string.destinations),
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp,
                        modifier = Modifier.padding(start = 24.dp)
                    )
                    if (destinations != null) {
                        Row(
                            modifier = Modifier
                                .padding(start = 20.dp)
                                .horizontalScroll(rememberScrollState())
                        ) {
                            destinations.forEach { destination ->
                                DestinationCard(
                                    modifier = Modifier.padding(0.dp),
                                    destinationCountry = destination.country,
                                    destinationCity = destination.city,
                                    destinationIATA = destination.iata,
                                )
                            }
                        }
                    }
                }
            } else if (filteredFlights!!.isEmpty()) {
                NoResultView()
            } else {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Row(Modifier.padding(top = 60.dp, start = 24.dp)) {
                        Text(
                            text = "Top Flights",
                            fontWeight = FontWeight.Bold,
                            fontSize = 26.sp,
                        )
                        Image(
                            painter = painterResource(id = R.drawable.fly),
                            contentDescription = "",
                            modifier = Modifier
                                .padding()
                                .size(27.dp)
                                .align(Alignment.CenterVertically)
                        )
                    }
                    Column {
                        flights!!.forEach { flight ->
                            val scheduleTime = LocalTime.parse(
                                flight.scheduleTime, DateTimeFormatter.ISO_LOCAL_TIME
                            )
                            val parsedDateTime = OffsetDateTime.parse(
                                flight.estimatedLandingTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME
                            )
                            val airlineData =
                                AirlineRepository.getAirlineDataByICAO(flight.prefixICAO)
                            val duration = java.time.Duration.between(
                                parsedDateTime.toLocalTime(), scheduleTime
                            )
                            FlightCard(
                                prefixICAO = flight.prefixICAO,
                                flightName = flight.flightName,
                                destination = flight.route.destinations[0],
                                modifier = Modifier,
                                airlineCompany = airlineData!!.name,
                                logo = painterResource(id = airlineData.logoResId),
                                scheduleDate = flight.scheduleDate,
                                scheduleTime = flight.scheduleTime,
                                estimatedLandingDate = parsedDateTime.toLocalDate().toString(),
                                estimatedLandingTime = parsedDateTime.toLocalTime().toString(),
                                duration = "${duration.toHours()}h${duration.toMinutes()}m"
                            )
                        }
                    }
                }
            }
        }
    }
    FRSearchBar(modifier = Modifier
        .padding(top = 220.dp)
        .height(40.dp)
        .padding(horizontal = 20.dp),
        onSearchButtonClick = { query ->
            if (query.isNotEmpty()) {
                filteredFlights = flights?.filter { flight ->
                    flight.flightName.contains(query, ignoreCase = true)
                }
                println("filtered: ${query.length}, $filteredFlights")
            }
        },
        onSearch = { query ->
            if (query.length >= 3) {
                timer.cancel()
                timer = Timer()
                timer.schedule(2000) {
                    filteredFlights = flights?.filter { flight ->
                        flight.flightName.contains(query, ignoreCase = true)
                    }
                    println("filtered: ${query.length}, $filteredFlights")
                }
            } else {
                filteredFlights = null
            }
        })
}