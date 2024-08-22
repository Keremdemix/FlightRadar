package com.keremdemir.flightradar.ui.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
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
fun FlightCard(
    onCardClicked: (id: String) -> Unit,
    flightItem: Flight,
    onFavouriteClicked: (id: String) -> Unit,
    onRemoveFavouriteClicked:(id:String)->Unit
) {
    val imageVectorLiveData = MutableLiveData(
        if (flightItem.isFavorite) {
            Icons.Filled.Favorite
        } else {
            Icons.Outlined.FavoriteBorder
        }
    )
    val imageVector: ImageVector? by imageVectorLiveData.observeAsState()

    val scheduleTime = LocalTime.parse(
        flightItem.scheduleTime, ISO_LOCAL_TIME
    )
    val parsedLandingDateTime = OffsetDateTime.parse(
        flightItem.estimatedLandingTime, ISO_OFFSET_DATE_TIME
    )
    val airlineData = AirlineRepository.getAirlineDataByICAO(flightItem.prefixICAO)
    val duration = java.time.Duration.between(
        scheduleTime, parsedLandingDateTime.toLocalTime()
    )
    Card(
        onClick = {
            onCardClicked(flightItem.id)
        },
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
                    text = ""
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

                Icon(
                    imageVector!!,
                    tint = colorResource(id = R.color.light_blue),// to force imageVector
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(24.dp)
                        .clickable {
                            if (flightItem.isFavorite){
                                onRemoveFavouriteClicked(flightItem.id)
                                flightItem.isFavorite=false
                            }else{
                                onFavouriteClicked(flightItem.id)
                                flightItem.isFavorite=true
                            }


                            imageVectorLiveData.value =
                                if (flightItem.isFavorite) {
                                    Icons.Filled.Favorite

                                } else {
                                    Icons.Outlined.FavoriteBorder
                                }
                        },
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
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = "arrow"
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
        }
    }
}