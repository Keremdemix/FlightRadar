package com.keremdemir.flightradar.ui.flights

import DataStorePreferences
import FavouriteViewModel
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.keremdemir.flightradar.R
import com.keremdemir.flightradar.ui.component.FRTop
import com.keremdemir.flightradar.ui.component.FlightCard
import com.keremdemir.flightradar.ui.component.TopBarConfig
import com.keremdemir.flightradar.ui.viewmodel.FlightsViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FlightsScreen(onCardClicked: (id: String) -> Unit, onFavouriteClicked: (id: String) -> Unit,onRemoveFavouriteClicked:(id:String)->Unit) {
    var checked by remember { mutableStateOf(false) }
    val flightsViewModel: FlightsViewModel = viewModel()
    val flights = flightsViewModel.flights.observeAsState().value
    val dataStore = DataStorePreferences(context = LocalContext.current)
    val favoriteViewModel = FavouriteViewModel(dataStore)

    FRTop(config = TopBarConfig(
        isHomeScreen = false,
        isBackButtonShown = false,
        isFavIconShown = true,
        title = "Flights"
    ), onBackButtonClicked = {})

    if (flights != null) {
        Column(
            Modifier
                .padding(top = 88.dp, bottom = 70.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    color = colorResource(id = R.color.light_blue),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    text = "Show Favourites"
                )
                Switch(
                    colors = SwitchDefaults.colors(
                        uncheckedBorderColor = colorResource(id = R.color.light_blue),
                        checkedThumbColor = colorResource(id = R.color.white),
                        checkedTrackColor = colorResource(id = R.color.light_blue),
                        uncheckedThumbColor = colorResource(id = R.color.light_blue),
                        uncheckedTrackColor = colorResource(id = R.color.white),
                    ),
                    checked = checked,
                    onCheckedChange = { isChecked ->
                        checked = isChecked
                        if (isChecked) {
                            flightsViewModel.showFavoriteItems(favoriteViewModel.loadFavouriteFlightIDList())
                        } else {
                            flightsViewModel.showAllItems()
                        }
                    },
                )
            }
            flights.forEach { flight ->
                FlightCard(onCardClicked = { id ->
                    onCardClicked(id)
                }, flightItem = flight, onFavouriteClicked = { id ->
                    onFavouriteClicked(id)
                }, onRemoveFavouriteClicked = {id->
                    onRemoveFavouriteClicked(id)
                })
            }

        }
    }
}