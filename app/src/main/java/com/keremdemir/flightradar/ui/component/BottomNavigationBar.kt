package com.keremdemir.flightradar.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.keremdemir.flightradar.R
import com.keremdemir.flightradar.ui.Screens

@Composable
fun FRBottomNavigationBar(navController: NavController,iconClicked: (String)->Unit) {
    BottomAppBar(
        windowInsets = WindowInsets(0, 0, 0, 0),
        modifier = Modifier
            .padding(bottom = 0.dp)
            .height(100.dp),
        containerColor = Color.Transparent,
        contentPadding = PaddingValues(0.dp),
    ) {
        Box(modifier = Modifier.background(color = Color.Transparent)) {
            Column(modifier = Modifier.background(color = Color.Transparent)) {
                Spacer(
                    modifier = Modifier
                        .background(color = Color.Transparent)
                        .height(30.dp)
                        .fillMaxWidth()
                )
                Row(
                    modifier = Modifier
                        .background(color = colorResource(R.color.light_blue))
                        .height(100.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(modifier = Modifier
                        .padding(horizontal = 60.dp)
                        .size(35.dp),
                        onClick = {
                            iconClicked(BottomNavItem.Flights.route)
                        }) {
                        Image(
                            painter = painterResource(R.drawable.fly),
                            contentDescription = "Flights",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    IconButton(modifier = Modifier
                        .padding(horizontal = 60.dp)
                        .size(35.dp),
                        onClick = {
                            iconClicked(BottomNavItem.Destinations.route)
                        }) {
                        Icon(
                            BottomNavItem.Destinations.icon,
                            contentDescription = BottomNavItem.Destinations.label,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = CircleShape)
                    .background(Color.White)
                    .align(Alignment.Center)
            ) {
                IconButton(
                    onClick = {
                        iconClicked(BottomNavItem.Home.route)
                    }, modifier = Modifier
                        .align(Alignment.Center)
                        .size(80.dp)
                ) {
                    Icon(
                        BottomNavItem.Home.icon,
                        contentDescription = BottomNavItem.Home.label,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem(Screens.Home.name, Icons.Default.Home, "Home")
    object Flights : BottomNavItem(Screens.Flights.name, Icons.Default.Face, "Flights")
    object Destinations : BottomNavItem(Screens.Destinations.name, Icons.Default.LocationOn, "Destinations")
}