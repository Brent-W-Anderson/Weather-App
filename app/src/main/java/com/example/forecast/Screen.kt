package com.example.forecast

sealed class Screen( val route: String ) {
    object Home: Screen( route = "home_screen" )
    object Forecast: Screen( route = "forecast_screen" )
}
