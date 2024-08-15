package com.keremdemir.flightradar.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.keremdemir.flightradar.R

@Composable
fun DestinationCard(
    modifier: Modifier = Modifier,
    cardModifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    destinationCity: String? = "",
    destinationCountry: String? = "",
    destinationIATA: String? = ""
) {
    Column(
        modifier = cardModifier
            .width(300.dp)
            .padding(top = 10.dp, end = 10.dp, start = 4.dp, bottom = 10.dp)
            .shadow(4.dp)
            .background(color = Color.White)
    ) {
        Column {
            Image(
                modifier = imageModifier
                    .padding(horizontal = 10.dp, vertical = 10.dp)
                    .fillMaxWidth(),
                painter = painterResource(R.drawable.scene),
                contentDescription = "Scene",
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (destinationCity != null) {
                    Text(text = destinationCity, fontWeight = FontWeight.Bold)
                }
                Text(text = destinationIATA ?: "", fontWeight = FontWeight.Bold)
            }
            Text(
                text = destinationCountry ?: "",
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 10.dp)
            )
        }
    }
}