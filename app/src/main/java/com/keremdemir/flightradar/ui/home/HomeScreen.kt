package com.keremdemir.flightradar.ui.home


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.keremdemir.flightradar.R
import com.keremdemir.flightradar.ui.component.FRSearchBar
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
                .padding(bottom = 100.dp)
                .fillMaxSize()
                .background(Color.White)
        ) {

            Top()

            if (filteredFlights == null) {
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

                    if (flights != null) {
                        Column {
                            flights.take(3).forEach { flight ->
                                FlightCard(
                                    modifier = Modifier.padding(0.dp),
                                    flightName = flight.flightName,
                                    prefixICAO = flight.prefixICAO,
                                    destination = flight.route.destinations[0]
                                )
                            }
                        }
                    }

                    Row(
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
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
                        LazyRow(modifier = Modifier.padding(start = 20.dp)) {
                            items(destinations) {
                                DestinationCard(modifier = Modifier)
                            }
                        }
                    }
                }
            } else if (filteredFlights!!.isEmpty()) {
                HomeEmptyView()
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
                            FlightCard(
                                modifier = Modifier.padding(0.dp),
                                flightName = flight.flightName,
                                prefixICAO = flight.prefixICAO,
                                destination = flight.route.destinations[0]
                            )
                        }
                    }
                }
            }
        }


    }

    FRSearchBar(
        modifier = Modifier
            .padding(top = 212.dp)
            .padding(horizontal = 24.dp),

        onSearchButtonClick = { query ->
            if (query.isNotEmpty()) {
                filteredFlights = flights?.filter { flight ->
                    flight.flightName.contains(query, ignoreCase = true)
                }
                println("filtered: ${query.length}, $filteredFlights")
            }
        },


        onSearch = { query ->
            timer.cancel()
            timer = Timer()
            timer.schedule(2000) {
                if (query.length >= 3) {

                    filteredFlights = flights?.filter { flight ->
                        flight.flightName.contains(query, ignoreCase = true)
                    }
                    println("filtered: ${query.length}, $filteredFlights")
                } else {
                    filteredFlights = null
                }

            }

        }
    )
}


@Composable
fun HomeEmptyView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .height(500.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.cross),
            contentDescription = "",
            modifier = Modifier
                .size(80.dp)

                .clip(CircleShape)
        )
        Text(
            modifier = Modifier.padding(top = 20.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            text = "There is no result."
        )
    }
}


@Composable
fun Top() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .background(
                colorResource(R.color.light_blue)
            )
    ) {
        Text(
            modifier = Modifier
                .padding(40.dp, 120.dp, 80.dp, 40.dp)
                .fillMaxWidth(),
            fontWeight = FontWeight.W700,
            color = Color.White,
            fontSize = 34.sp,
            text = stringResource(id = R.string.home_header)
        )
    }
}


@Composable
fun FlightCard(modifier: Modifier, flightName: String, prefixICAO: String, destination: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 5.dp),
        shape = CardDefaults.shape,
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            modifier = Modifier

                .clip(shape = RoundedCornerShape(15.dp))
                .border(1.dp, color = Color.Gray, shape = RoundedCornerShape(15.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = colorResource(R.color.light_blue))
            ) {
                Text(
                    modifier = Modifier
                        .padding(5.dp),
                    color = Color.White,
                    fontSize = 24.sp,
                    text = "Istanbul-Ankara"
                )
            }
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,

                ) {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                    )
                    Column(
                        modifier = Modifier
                            .height(80.dp)
                            .padding(start = 10.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            modifier = Modifier,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            text = stringResource(id = R.string.company_name)
                        )
                        Text(
                            modifier = Modifier
                                .padding(5.dp),
                            fontSize = 22.sp,
                            color = Color.Gray,
                            text = flightName
                        )
                    }
                }
                Image(
                    modifier = Modifier
                        .size(32.dp),
                    alignment = Alignment.Center,
                    painter = painterResource(R.drawable.fav_icon),
                    contentDescription = "favourite icon"
                )
            }
            HorizontalDivider()
            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column {
                    CardDateText(modifier = Modifier, prefixICAO)
                    CardDateText(modifier = Modifier, "8.00")
                    CardDateText(modifier = Modifier, "T7 05/02/2024")
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = stringResource(id = R.string.flight_duration))
                    Image(
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = "arrow"
                    )

                }
                Column {
                    CardDateText(modifier = Modifier, destination)
                    CardDateText(modifier = Modifier, "9.00")
                    CardDateText(modifier = Modifier, "T7 05/02/2024")

                }
            }

        }
    }

}


@Composable
fun CardDateText(modifier: Modifier, myString: String) {
    Text(
        fontSize = 18.sp,
        fontWeight = FontWeight.W500,
        text = myString
    )
}


@Composable
fun DestinationCard(

    modifier: Modifier = Modifier,
    cardModifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,

    ) {
    Column(
        modifier = cardModifier
            .width(300.dp)
            .padding(top = 10.dp, end = 10.dp, start = 4.dp, bottom = 10.dp)
            .shadow(4.dp)
            .background(color = Color.White)
    ) {
        Column {
            Image(
                modifier = imageModifier
                    .padding(horizontal = 10.dp, vertical = 10.dp)
                    .fillMaxWidth(),
                painter = painterResource(R.drawable.scene),
                contentDescription = "Scene",
                contentScale = ContentScale.Crop

            )

            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Istanbul", fontWeight = FontWeight.Bold)
                Text(text = "SAW", fontWeight = FontWeight.Bold)
            }
            Text(
                text = "Turkey",
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 10.dp)
            )
        }
    }

}


