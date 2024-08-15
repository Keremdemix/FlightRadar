package com.keremdemir.flightradar.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.keremdemir.flightradar.R

@Composable
fun NoResultView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .height(500.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.cross),
            contentDescription = "",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )
        Text(
            modifier = Modifier.padding(top = 20.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            text = "There is no result."
        )
    }
}