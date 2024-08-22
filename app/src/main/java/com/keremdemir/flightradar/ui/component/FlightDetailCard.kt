package com.keremdemir.flightradar.ui.component

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.keremdemir.flightradar.R
import com.keremdemir.flightradar.data.AirlineRepository
import com.keremdemir.flightradar.data.model.Flight
import com.keremdemir.flightradar.utils.Utils
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter.ISO_LOCAL_TIME
import java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FlightDetailsCard(
    flightItem: Flight
) {
    val scheduleTime = LocalTime.parse(
        flightItem.scheduleTime, ISO_LOCAL_TIME
    )
    val parsedLandingDateTime = OffsetDateTime.parse(
        flightItem.estimatedLandingTime, ISO_OFFSET_DATE_TIME
    )
    val parsedActualDateTime = OffsetDateTime.parse(
        flightItem.actualLandingTime, ISO_OFFSET_DATE_TIME
    )
    val airlineData = AirlineRepository.getAirlineDataByICAO(flightItem.prefixICAO)
    val duration = java.time.Duration.between(
        scheduleTime, parsedLandingDateTime.toLocalTime()
    )

    Column(modifier = Modifier.padding(horizontal = 30.dp)) {
        Row(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row {
                airlineData?.let { painterResource(id = it.logoResId) }?.let {
                    Image(
                        painter = it,
                        contentDescription = "airline logo",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                    )
                }
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 10.dp),
                ) {
                    airlineData?.let {
                        Text(
                            modifier = Modifier.padding(bottom = 4.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            text = it.name
                        )
                    }
                    Text(
                        fontSize = 12.sp, color = Color.Gray, text = flightItem.flightName
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
                    text = flightItem.prefixICAO, fontSize = 14.sp, fontWeight = FontWeight.Bold
                )
                Text(
                    text = flightItem.scheduleTime, fontSize = 12.sp
                )
                Text(
                    text = flightItem.scheduleDate, fontSize = 12.sp
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    color = colorResource(id = R.color.grey_300),
                    text = Utils.formatDuration(duration)
                )
                Image(
                    painter = painterResource(id = R.drawable.arrow), contentDescription = "arrow"
                )
            }
            Column {
                Text(
                    text = flightItem.route.destinations[0],
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = Utils.formatLocalTime(parsedLandingDateTime), fontSize = 12.sp
                )
                Text(
                    text = Utils.formatLocalDate(parsedLandingDateTime), fontSize = 12.sp
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
                LightText(flightItem.flightDirection)
            }
            Column {
                BoldText(stringResource(id = R.string.flight_name))
                LightText(flightItem.flightName)
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
                LightText(flightItem.id)
            }
            Column {
                BoldText(stringResource(id = R.string.iata_prefix))
                LightText(flightItem.prefixIATA)
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
                LightText(
                    flightItem.publicFlightState.flightStates.joinToString(
                        separator = ", "
                    ),
                )
            }
            Column {
                BoldText(stringResource(id = R.string.baggage_claim))
                flightItem.baggageClaim?.belts?.let { LightText(it.joinToString(separator = ", ")) }
            }
        }
        HorizontalDivider(
            Modifier
                .padding(vertical = 10.dp)
                .align(Alignment.CenterHorizontally)
        )
        Column {
            BoldText(stringResource(id = R.string.estimated_landing_time))
            LightText(
                "${Utils.formatLocalDate(parsedLandingDateTime)}    ${
                    Utils.formatLocalTime(
                        parsedLandingDateTime
                    )
                }"
            )
        }
        HorizontalDivider(
            Modifier
                .padding(vertical = 10.dp)
                .align(Alignment.CenterHorizontally)
        )
        Column {
            BoldText(stringResource(id = R.string.actual_landing_time))
            LightText(
                "${Utils.formatLocalDate(parsedActualDateTime)}    ${
                    Utils.formatLocalTime(
                        parsedActualDateTime
                    )
                }"
            )
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
