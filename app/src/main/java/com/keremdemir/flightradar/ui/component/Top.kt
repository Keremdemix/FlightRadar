package com.keremdemir.flightradar.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.keremdemir.flightradar.R

@Composable
fun FRTop(config: TopBarConfig) {
    if (config.isHomeScreen) HomeTopBar(config.title)
    else DefaultTopBar(config)

}


@Composable
fun HomeTopBar(title: String) {
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
            text = title
        )
    }
}

@Composable
fun DefaultTopBar(config: TopBarConfig) {
    Row(
        modifier = Modifier
            .height(90.dp)
            .fillMaxWidth()
            .background(colorResource(id = R.color.light_blue)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 20.dp)
        ) {
            if (config.isBackButtonShown) IconButton(onClick = { }) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    imageVector = Icons.Outlined.KeyboardArrowLeft,
                    contentDescription = "",
                    tint = Color.White

                )
            }

            Text(
                config.title,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 20.dp)
            )
        }
        if (config.isFavIconShown) Icon(
            modifier = Modifier
                .padding(top = 20.dp, end = 32.dp)
                .size(26.dp),
            painter = painterResource(id = R.drawable.fav_icon),
            tint = Color.White,
            contentDescription = ""
        )
    }
}


data class TopBarConfig(
    val isHomeScreen: Boolean,
    val isFavIconShown: Boolean = false,
    val isBackButtonShown: Boolean = false,
    val title: String
)