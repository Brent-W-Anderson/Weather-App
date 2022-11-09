package com.example.forecast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage

@Composable
fun HomeScreen(
    viewModel: CurrentConditionsViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by viewModel.currentConditions.collectAsState(null)

    LaunchedEffect(Unit) {
        viewModel.fetchData()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ActionBar()
        state?.let {
            CurrentConditionsContent(it)
            ForecastButton( navController )
        }
    }
}

@Composable
fun ActionBar() {
    Text(
        text = stringResource( R.string.app_name ),
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(R.color.purple_700))
            .padding(all = 16.dp),
        color = Color.White,
        fontSize = 16.sp
    )
}

@Composable
fun CurrentConditionsContent(
    currentConditions: CurrentConditions
) {
    Text(
        text = stringResource( R.string.city_state ),
        modifier = Modifier.padding( top = 16.dp ),
        fontSize = 14.sp
    )
    Column() {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column() {
                Text(
                    text = stringResource( R.string.temp, currentConditions.conditions.temperature.toInt() ),
                    fontSize = 42.sp
                )
                Text(
                    text = stringResource( R.string.feels_like, currentConditions.conditions.feelsLike.toInt() ),
                    fontSize = 14.sp
                )
            }
            AsyncImage(
                model = String.format("https://openweathermap.org/img/wn/%s@4x.png", currentConditions.weatherData.firstOrNull()?.iconName),
                contentDescription = null,
                modifier = Modifier.size( 128.dp )
            )
        }
        Text(
            text = stringResource( R.string.low_temp, currentConditions.conditions.minTemp.toInt() ),
            fontSize = 16.sp,
            modifier = Modifier.padding( start = 32.dp )
        )
        Text(
            text = stringResource( R.string.high_temp, currentConditions.conditions.maxTemp.toInt() ),
            fontSize = 16.sp,
            modifier = Modifier.padding( start = 32.dp )
        )
        Text(
            text = stringResource( R.string.humidity, currentConditions.conditions.humidity.toInt() ) + "%",
            fontSize = 16.sp,
            modifier = Modifier.padding( start = 32.dp )
        )
        Text(
            text = stringResource( R.string.pressure, currentConditions.conditions.pressure.toInt() ),
            fontSize = 16.sp,
            modifier = Modifier.padding( start = 32.dp )
        )
    }
}

@Composable
fun ForecastButton( navController: NavController ) {
    Button(
        onClick = {
            navController.navigate( route = Screen.Forecast.route )
        },
        modifier = Modifier.padding( top = 16.dp )
    ) {
        Text(text = stringResource( R.string.forecast ))
    }
}

@Composable
@Preview( showBackground = true )
fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController()
    )
}