package com.example.forecast

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    viewModel: CurrentConditionsViewModel = hiltViewModel(),
    hasLocationPermission: Boolean,
//    latitudeLongitude: LatitudeLongitude,
    lat: Double,
    lon: Double,
    goToNext: MutableState<Boolean>
) {
    val state by viewModel.currentConditions.collectAsState(null)

    if( hasLocationPermission ) {
        Log.e( "LAT: ", "$lat" )
        Log.e( "LON: ", "$lon" )

        viewModel.fetchData( lat, lon )
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {
        composable(
            route = Screen.Home.route
        ) {
            state?.let {
                HomeScreen(
                    it,
                    navController,
                    hasLocationPermission,
//                    latitudeLongitude,
                    goToNext
                )
            }
        }
        composable(
            route = Screen.Forecast.route
        ) {
            state?.let { Forecast(it) }
        }
    }
}