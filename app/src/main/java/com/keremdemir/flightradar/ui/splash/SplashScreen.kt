package com.keremdemir.flightradar.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.keremdemir.flightradar.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onButtonClicked:() -> Unit) {
    Scaffold (
        Modifier.fillMaxSize(),
        containerColor = colorResource(R.color.light_blue)
    ){ innerPadding ->
        Column (modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally ){

            Image(
                painter = painterResource(id = R.drawable.ic_app_flight_icon),
                contentDescription = "",
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally))
            Text(text = stringResource(id = R.string.app_name), modifier = Modifier.padding(15.dp), color = Color.White, fontSize = 32.sp)

            LaunchedEffect(Unit) {
                delay(5000)
                onButtonClicked()
            }
        }

    }

}
