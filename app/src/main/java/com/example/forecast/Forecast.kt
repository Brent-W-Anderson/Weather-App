package com.example.forecast

import androidx.compose.foundation.Image
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun Forecast() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState)
    ){
        ForecastActionBar()

        // Copy 4 of the same rows 4 times, so we have 16 forecast items.
        // These could be randomly generated values, but I wasn't sure if we were still using the strings.xml
        for( i in 1..4 ) {
            // day 1 forecast condition
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                ForecastItem(
                    stringResource( R.string.day1_img_url ),
                    stringResource( R.string.day1_date ),
                    stringResource( R.string.day1_temp ),
                    stringResource( R.string.day1_high_temp ),
                    stringResource( R.string.day1_low_temp ),
                    stringResource( R.string.day1_sunrise ),
                    stringResource( R.string.day1_sunset )
                )
            }
            Divider(color = Color.Gray, thickness = 0.5.dp)

            // day 2 forecast condition
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                ForecastItem(
                    stringResource( R.string.day2_img_url ),
                    stringResource( R.string.day2_date ),
                    stringResource( R.string.day2_temp ),
                    stringResource( R.string.day2_high_temp ),
                    stringResource( R.string.day2_low_temp ),
                    stringResource( R.string.day2_sunrise ),
                    stringResource( R.string.day2_sunset )
                )
            }
            Divider(color = Color.Gray, thickness = 0.5.dp)

            // day 3 forecast condition
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                ForecastItem(
                    stringResource( R.string.day3_img_url ),
                    stringResource( R.string.day3_date ),
                    stringResource( R.string.day3_temp ),
                    stringResource( R.string.day3_high_temp ),
                    stringResource( R.string.day3_low_temp ),
                    stringResource( R.string.day3_sunrise ),
                    stringResource( R.string.day3_sunset )
                )
            }
            Divider(color = Color.Gray, thickness = 0.5.dp)

            // day 4 forecast condition
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                ForecastItem(
                    stringResource( R.string.day4_img_url ),
                    stringResource( R.string.day4_date ),
                    stringResource( R.string.day4_temp ),
                    stringResource( R.string.day4_high_temp ),
                    stringResource( R.string.day4_low_temp ),
                    stringResource( R.string.day4_sunrise ),
                    stringResource( R.string.day4_sunset )
                )
            }
            Divider(color = Color.Gray, thickness = 0.5.dp)
        }
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

@Composable
fun ForecastItem(
    imgUrl: String,
    date: String,
    temp: String,
    high: String,
    low: String,
    sunrise: String,
    sunset: String
) {
    Image(
        painter = rememberAsyncImagePainter( imgUrl ),
        contentDescription = null,
        modifier = Modifier.size( 64.dp )
    )
    Text(
        text = date,
        fontSize = 14.sp
    )
    Column( modifier = Modifier.padding(horizontal = 16.dp) ) {
        Text(
            text = temp,
            fontSize = 14.sp
        )
        Text(
            text = high,
            fontSize = 14.sp
        )
    }
    Column( modifier = Modifier.padding(end = 16.dp) ) {
        Spacer( modifier = Modifier.height( 16.dp) )
        Text(
            text = low,
            fontSize = 14.sp
        )
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = sunrise,
            fontSize = 14.sp
        )
        Text(
            text = sunset,
            fontSize = 14.sp
        )
    }
}

@Composable
@Preview( showBackground = true )
fun ForecastPreview() {
    Forecast()
}