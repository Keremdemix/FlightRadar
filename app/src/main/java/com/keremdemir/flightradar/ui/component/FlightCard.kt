package com.keremdemir.flightradar.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.keremdemir.flightradar.R

@Composable
fun FlightCard(
    modifier: Modifier,
    flightName: String,
    prefixICAO: String,
    destination: String,
    airlineCompany: String,
    logo: Painter,
    scheduleTime: String,
    scheduleDate: String,
    estimatedLandingTime: String,
    estimatedLandingDate: String,
    duration: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp),
        shape = CardDefaults.shape,
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(15.dp))
                .border(
                    1.dp,
                    color = colorResource(id = R.color.grey_100),
                    shape = RoundedCornerShape(15.dp)
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(37.dp)
                    .background(color = colorResource(R.color.light_blue))
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .align(Alignment.CenterStart),
                    color = Color.White,
                    fontSize = 14.sp,
                    text = "İstanbul - Ankara"
                )
            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 10.dp)
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
                Image(
                    modifier = Modifier.size(24.dp),
                    alignment = Alignment.Center,
                    painter = painterResource(R.drawable.fav_icon),
                    contentDescription = "favourite icon"
                )
            }
            HorizontalDivider(
                Modifier
                    .padding(horizontal = 20.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 10.dp)
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
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = "arrow"
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
        }
    }
}
