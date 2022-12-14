package com.example.forecast.service

import com.example.forecast.CurrentConditions
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapApi {

    @GET("data/2.5/forecast/daily")
    suspend fun getCurrentConditions(
        @Query("zip") zip: Int,
        @Query("appid") apikey: String,
        @Query("cnt") count: Int,
        @Query("units") units: String = "imperial"
    ) : CurrentConditions
}