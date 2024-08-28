package com.keremdemir.flightradar.ui

import DataStorePreferences
import FavouriteViewModel
import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.keremdemir.flightradar.ui.component.BottomNavItem
import com.keremdemir.flightradar.ui.component.FRBottomNavigationBar
import com.keremdemir.flightradar.ui.destinations.DestinationsScreen
import com.keremdemir.flightradar.ui.flightdetails.FlightDetailsScreen
import com.keremdemir.flightradar.ui.flights.FlightsScreen
import com.keremdemir.flightradar.ui.home.HomeScreen
import com.keremdemir.flightradar.ui.splash.SplashScreen


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FlightRadarApp() {

    val navController = rememberNavController()
    val dataStore = DataStorePreferences(context = LocalContext.current)
    val favoriteViewModel = FavouriteViewModel(dataStore)

    Column {
        Scaffold(bottomBar = {

            val navBackStackEntry = navController.currentBackStackEntryAsState()
            if (navBackStackEntry.value?.destination?.route != Screens.Splash.name) {
                FRBottomNavigationBar(navController = navController) { route ->
                    navController.navigate(route) {
                        popUpTo(BottomNavItem.Home.route)
                       // launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        }) {

            NavGraph(navController = navController, favoriteViewModel = favoriteViewModel)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(navController: NavHostController, favoriteViewModel: FavouriteViewModel) {
    NavHost(navController = navController, startDestination = Screens.Splash.name) {

        composable(route = Screens.Splash.name) {
            SplashScreen(navigateWhenDataFetch = {
                navController.navigate(Screens.Home.name) {
                    popUpTo(Screens.Splash.name) {
                        inclusive = true
                    }
                }
            })
        }

        composable(route = Screens.Home.name) {
            HomeScreen(onFlightButtonClick = { navController.navigate(Screens.Flights.name) },
                onCardClicked = { id ->
                    navController.navigate("${Screens.FlightDetails.name}/$id")
                }, onFavouriteClicked = { id ->
                    favoriteViewModel.addFavouriteFlightIDList(id)
                    Log.d("KEREM DEMİR", id)
                }, onRemoveFavouriteClicked = {id->
                    favoriteViewModel.removeFavouriteFlightID(id)
                }
            )
        }

        composable(route = Screens.Destinations.name) {
            DestinationsScreen()
        }

        composable(route = Screens.Flights.name) {
            FlightsScreen(onFavouriteClicked = { id ->
                favoriteViewModel.addFavouriteFlightIDList(id)
                Log.d("KEREM DEMİR", id)
            }, onCardClicked = {id->
                navController.navigate("${Screens.FlightDetails.name}/$id")
            }, onRemoveFavouriteClicked = {id->
                favoriteViewModel.removeFavouriteFlightID(id)
            })
        }

        composable(
            route = "${Screens.FlightDetails.name}/{id}",
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            if (id != null) {
                FlightDetailsScreen(
                    onClickedFlightID = id,
                    onBackButtonClicked = { navController.popBackStack() })
            }
        }
    }
}

enum class Screens {
    Splash, Home, Flights, Destinations, FlightDetails
}