package com.example.forecast.service

import com.example.forecast.CurrentConditions
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapApi {

    @GET("data/2.5/weather")
    suspend fun getCurrentConditions(
        @Query("zip") zip: String,
        @Query("appid") apikey: String,
        @Query("units") units: String = "imperial"
    ) : CurrentConditions
}