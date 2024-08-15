package com.keremdemir.flightradar.ui.home


import android.annotation.SuppressLint
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
import java.util.Timer
import kotlin.concurrent.schedule

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
                                val airlineData =
                                    AirlineRepository.getAirlineDataByICAO(flight.prefixICAO)
                                FlightCard(
                                    modifier = Modifier.padding(0.dp),
                                    flightName = flight.flightName,
                                    prefixICAO = flight.prefixICAO,
                                    destination = flight.route.destinations[0],
                                    airlineCompany = airlineData!!.name,
                                    logo = painterResource(id = airlineData.logoResId)
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
                            Text(text = "See More Flights...")
                        }
                        TextButton(onClick = onFavouriteButtonClick) {
                            Text(text = "See Favourite Flights...")
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
                        filteredFlights!!.forEach { flight ->
                            val airlineData =
                                AirlineRepository.getAirlineDataByICAO(flight.prefixICAO)
                            FlightCard(
                                modifier = Modifier.padding(0.dp),
                                flightName = flight.flightName,
                                prefixICAO = flight.prefixICAO,
                                destination = flight.route.destinations[0],
                                airlineCompany = airlineData!!.name,
                                logo = painterResource(id = airlineData.logoResId)
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