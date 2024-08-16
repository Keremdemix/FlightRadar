package com.keremdemir.flightradar.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.keremdemir.flightradar.ui.component.FRBottomNavigationBar
import com.keremdemir.flightradar.ui.destinations.DestinationsScreen
import com.keremdemir.flightradar.ui.flights.FlightsScreen
import com.keremdemir.flightradar.ui.home.HomeScreen
import com.keremdemir.flightradar.ui.splash.SplashScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FlightRadarApp() {

    val navController = rememberNavController()

    Column {
        Scaffold(bottomBar = {
            val navBackStackEntry = navController.currentBackStackEntryAsState()
            if (navBackStackEntry.value?.destination?.route != Screens.Splash.name) {
                FRBottomNavigationBar(navController = navController)
            }
        }) {
            NavGraph(navController = navController)
        }
    }
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.Splash.name) {

        composable(route = Screens.Splash.name) {
            SplashScreen(navigateWhenDataFetch = {
                navController.navigate(Screens.Home.name)
            })
        }

        composable(route = Screens.Home.name) {
            HomeScreen(onFlightButtonClick = { navController.navigate(Screens.Flights.name) },
                onFavouriteButtonClick = {})
        }

        composable(route = Screens.Destinations.name) {
            DestinationsScreen()
        }

        composable(route = Screens.Flights.name) {
            FlightsScreen()
        }
    }
}

enum class Screens {
    Splash, Home, Flights, Destinations
}