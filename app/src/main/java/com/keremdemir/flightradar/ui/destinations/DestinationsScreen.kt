package com.keremdemir.flightradar.ui.destinations

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.keremdemir.flightradar.ui.component.DestinationCard
import com.keremdemir.flightradar.ui.viewmodel.DestinationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DestinationsScreen() {

    val destinationViewModel: DestinationViewModel = viewModel()
    val destinations = destinationViewModel.destinations.observeAsState().value?.destinations

    Scaffold(topBar = {
        TopAppBar(colors = topAppBarColors(
            containerColor = colorResource(R.color.light_blue),
        ), title = {
            Text(
                stringResource(id = R.string.destinations),
                color = Color.White,
            )
        })
    }) { _ ->
        if (!destinations.isNullOrEmpty()) {
            Column(
                Modifier
                    .padding(vertical = 100.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                destinations.take(3).forEach { destination ->
                    DestinationCard(
                        imageModifier = Modifier
                            .height(100.dp)
                            .padding(),
                        cardModifier = Modifier.width(400.dp),
                        destinationItem = destination
                    )
                }
            }
        }
    }
}