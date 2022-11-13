package com.example.forecast

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun Forecast(
        currentConditions: CurrentConditions,
    ) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState)
    ){
        ForecastActionBar()
        ForecastContent(currentConditions)
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ForecastContent(
    currentConditions: CurrentConditions
) {
    for( i in 0 until currentConditions.count) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ForecastItem(
                i,
                currentConditions.conditions[i].weather[0].icon,
                currentConditions.conditions[i].temp.day.toInt(),
                currentConditions.conditions[i].temp.max.toInt(),
                currentConditions.conditions[i].temp.min.toInt(),
                currentConditions.conditions[i].sunrise.toString(),
                currentConditions.conditions[i].sunset.toString(),
            )
        }
        Divider(color = Color.Gray, thickness = 0.5.dp)
    }
}

@Composable
fun ForecastActionBar() {
    Text(
        text = stringResource( R.string.forecast_name ),
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(R.color.purple_700))
            .padding(all = 16.dp),
        color = Color.White,
        fontSize = 16.sp
    )
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ForecastItem(
    day: Int,
    imgUrl: String,
    temp: Int,
    high: Int,
    low: Int,
    sunrise: String,
    sunset: String
) {
    val date = LocalDate.now().plusDays(day.toLong())
    val formatter = DateTimeFormatter.ofPattern("MMM dd")
    val formatted = date.format(formatter)

    AsyncImage(
        model = "https://openweathermap.org/img/wn/$imgUrl@2x.png",
        contentDescription = null,
        modifier = Modifier.size( 64.dp )
    )
    Text(
        text = formatted.toString(),
        fontSize = 14.sp
    )
    Column( modifier = Modifier.padding(horizontal = 16.dp) ) {
        Text(
            text = stringResource( R.string.temp, temp ),
            fontSize = 14.sp
        )
        Text(
            text = stringResource( R.string.high_temp, high ),
            fontSize = 14.sp
        )
    }
    Column( modifier = Modifier.padding(end = 16.dp) ) {
        Spacer( modifier = Modifier.height( 16.dp) )
        Text(
            text = stringResource( R.string.low_temp, low ),
            fontSize = 14.sp
        )
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = "Sunrise: 8:00am",
            fontSize = 14.sp
        )
        Text(
            text = "Sunset: 8:00pm",
            fontSize = 14.sp
        )
    }
}
