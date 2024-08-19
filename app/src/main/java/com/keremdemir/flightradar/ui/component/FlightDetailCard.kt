package com.keremdemir.flightradar.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.keremdemir.flightradar.R

@Composable
fun FlightDetailsCard(
    modifier: Modifier,
    flightDirection: String,
    flightName: String,
    gate: String,
    prefixIATA: String,
    flightState: String,
    baggageClaim: String,
    prefixICAO: String,
    destination: String,
    airlineCompany: String,
    logo: Painter,
    scheduleTime: String,
    scheduleDate: String,
    estimatedLandingTime: String,
    estimatedLandingDate: String,
    actualLandingTime: String?,
    actualLandingDate: String?,
    duration: String
) {
    Column(modifier = Modifier.padding(horizontal = 30.dp)) {
        Row(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row {
                Image(
                    painter = logo,
                    contentDescription = "airline logo",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 10.dp),
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 4.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        text = airlineCompany
                    )
                    Text(
                        fontSize = 12.sp, color = Color.Gray, text = flightName
                    )
                }
            }

        }
        HorizontalDivider(
            Modifier
                .padding(vertical = 10.dp)
                .align(Alignment.CenterHorizontally)
        )
        Row(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Text(
                    text = prefixICAO, fontSize = 14.sp, fontWeight = FontWeight.Bold
                )
                Text(
                    text = scheduleTime, fontSize = 12.sp
                )
                Text(
                    text = scheduleDate, fontSize = 12.sp
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    color = colorResource(id = R.color.grey_300), text = duration.toString()
                )
                Image(
                    painter = painterResource(id = R.drawable.arrow), contentDescription = "arrow"
                )
            }
            Column {
                Text(
                    text = destination, fontSize = 14.sp, fontWeight = FontWeight.Bold
                )
                Text(
                    text = estimatedLandingTime, fontSize = 12.sp
                )
                Text(
                    text = estimatedLandingDate, fontSize = 12.sp
                )
            }
        }
        HorizontalDivider(
            Modifier
                .padding(vertical = 10.dp)
                .align(Alignment.CenterHorizontally)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Column(Modifier.width(200.dp)) {
                BoldText(stringResource(id = R.string.flight_direction))
                LightText(flightDirection)
            }
            Column {
                BoldText(stringResource(id = R.string.flight_name))
                LightText(flightName)
            }
        }
        HorizontalDivider(
            Modifier
                .padding(vertical = 10.dp)
                .align(Alignment.CenterHorizontally)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(Modifier.width(200.dp)) {
                BoldText(stringResource(id = R.string.gate))
                LightText(gate)
            }
            Column {
                BoldText(stringResource(id = R.string.iata_prefix))
                LightText(prefixIATA)
            }
        }
        HorizontalDivider(
            Modifier
                .padding(vertical = 10.dp)
                .align(Alignment.CenterHorizontally)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(Modifier.width(200.dp)) {
                BoldText(stringResource(id = R.string.flight_state))
                LightText(flightState.toString())
            }
            Column {
                BoldText(stringResource(id = R.string.baggage_claim))
                LightText(baggageClaim.toString())
            }
        }
        HorizontalDivider(
            Modifier
                .padding(vertical = 10.dp)
                .align(Alignment.CenterHorizontally)
        )
        Column {
            BoldText(stringResource(id = R.string.estimated_landing_time))
            LightText("$estimatedLandingDate    $estimatedLandingTime")
        }
        HorizontalDivider(
            Modifier
                .padding(vertical = 10.dp)
                .align(Alignment.CenterHorizontally)
        )
        Column {
            BoldText(stringResource(id = R.string.actual_landing_time))
            if (actualLandingTime != null) {
                LightText("$actualLandingDate    $actualLandingTime")
            }
        }
    }
}

@Composable
fun BoldText(string: String) {
    Text(
        text = string, fontWeight = FontWeight.Bold, fontSize = 14.sp
    )
}

@Composable
fun LightText(string: String) {
    Text(
        text = string, fontSize = 12.sp
    )
}
