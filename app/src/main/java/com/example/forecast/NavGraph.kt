package com.example.forecast

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    viewModel: CurrentConditionsViewModel = hiltViewModel()
) {
    val state by viewModel.currentConditions.collectAsState(null)

    LaunchedEffect(Unit) {
        viewModel.fetchData()
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            state?.let { HomeScreen(it, navController ) }
        }
        composable(
            route = Screen.Forecast.route
        ) {
            state?.let { Forecast(it) }
        }
    }
}