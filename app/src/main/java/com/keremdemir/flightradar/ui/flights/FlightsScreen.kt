package com.keremdemir.flightradar.ui.flights

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.keremdemir.flightradar.ui.home.Top

@Composable fun FlightsScreen(){
    Scaffold (
        bottomBar = {
        }
    ) { _ ->
        Box {
            Column(
                modifier = Modifier
                    .padding(bottom = 120.dp)
                    .fillMaxSize()
                    .background(Color.White)
            ) {

                Top()

            }
        }
    }
}