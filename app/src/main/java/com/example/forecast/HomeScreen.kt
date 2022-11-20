package com.example.forecast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun HomeScreen(
    currentConditions: CurrentConditions,
    navController: NavController,
    hasLocationPermission: Boolean,
//    latitudeLongitude: LatitudeLongitude,
    goToNext: MutableState<Boolean>
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ActionBar()
        if( goToNext.value ) {
            CurrentConditionsContent(currentConditions)
            ForecastButton( navController )
        }
        else {
            LocationButton( goToNext )
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
        text = currentConditions.city.name + ", " + currentConditions.city.country,
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
                    text = stringResource( R.string.temp, currentConditions.conditions[0].temp.day.toInt() ),
                    fontSize = 42.sp
                )
                Text(
                    text = stringResource( R.string.feels_like, currentConditions.conditions[0].feelsLike.day.toInt() ),
                    fontSize = 14.sp
                )
            }
            AsyncImage(
                model = String.format("https://openweathermap.org/img/wn/%s@4x.png", currentConditions.conditions[0].weather[0].icon),
                contentDescription = null,
                modifier = Modifier.size( 128.dp )
            )
        }
        Text(
            text = stringResource( R.string.low_temp, currentConditions.conditions[0].temp.min.toInt() ),
            fontSize = 16.sp,
            modifier = Modifier.padding( start = 32.dp )
        )
        Text(
            text = stringResource( R.string.high_temp, currentConditions.conditions[0].temp.max.toInt() ),
            fontSize = 16.sp,
            modifier = Modifier.padding( start = 32.dp )
        )
        Text(
            text = stringResource( R.string.humidity, currentConditions.conditions[0].humidity.toInt() ) + "%",
            fontSize = 16.sp,
            modifier = Modifier.padding( start = 32.dp )
        )
        Text(
            text = stringResource( R.string.pressure, currentConditions.conditions[0].pressure.toInt() ),
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
fun LocationButton( goToNext: MutableState<Boolean> ) {
    Button(
        onClick = { goToNext.value = true },
        modifier = Modifier.padding( top = 16.dp )
    ) {
        Text(text = stringResource( R.string.getCondition ))
    }
}
