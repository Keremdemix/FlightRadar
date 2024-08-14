package com.keremdemir.flightradar.ui.flights

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.keremdemir.flightradar.R
import com.keremdemir.flightradar.ui.viewmodel.FlightsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightsScreen() {

    val flightsViewModel: FlightsViewModel = viewModel()
    val flights = flightsViewModel.flights.observeAsState().value?.flights

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = colorResource(R.color.light_blue),
                ),
                title = {
                    Text(
                        stringResource(id = R.string.flights),
                        color = Color.White,
                    )
                },
            )
        }
    ) { _ ->
        flights?.let {

            Column(
                modifier = Modifier
                    .padding(bottom = 120.dp)
                    .fillMaxSize()
                    .background(Color.White)
            ) {

            }
        }

    }

}

