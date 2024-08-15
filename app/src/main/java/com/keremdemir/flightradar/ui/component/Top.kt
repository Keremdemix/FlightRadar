package com.keremdemir.flightradar.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.keremdemir.flightradar.R

@Composable
fun Top() {
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
            text = stringResource(id = R.string.home_header)
        )
    }
}