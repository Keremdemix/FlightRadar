package com.keremdemir.flightradar.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.keremdemir.flightradar.ui.home.HomeScreen
import com.keremdemir.flightradar.ui.splash.SplashScreen

@Composable
fun FlightRadarApp(){
    val navController= rememberNavController()
    NavHost( navController =navController, startDestination = "splash" ) {

        composable(route = "splash"){
            SplashScreen(onButtonClicked = {
                navController.navigate("home")
            })
        }

        /*composable(route = "login"){
            LoginScreen (onButtonClick = {
                navController.navigate("register")
            }, onLoginButtonClick = {
                navController.navigate("home")

            })
        }*/

        /*composable(route = "register"){
            RegisterScreen (onButtonClicked = {
                println("Clicked")
                navController.popBackStack()
            })
        }*/


        composable(route = "home"){
            HomeScreen()
        }


    }
}