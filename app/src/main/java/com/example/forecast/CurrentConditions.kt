package com.example.forecast

import com.squareup.moshi.Json

data class CityData(
    @Json(name = "name") val name: String,
    @Json(name = "country") val country: String,
)

data class WeatherData(
    @Json(name = "icon") val icon: String
)

data class TempData(
    @Json(name = "day") val day: Float,
    @Json(name = "min") val min: Float,
    @Json(name = "max") val max: Float
)

data class FeelsLikeData(
    @Json(name = "day") val day: Float
)

data class CurrentConditionsData(
    @Json(name = "sunrise") val sunrise: Long,
    @Json(name = "sunset") val sunset: Long,
    @Json(name = "weather") val weather: List<WeatherData>,
    @Json(name = "temp") val temp: TempData,
    @Json(name = "feels_like") val feelsLike: FeelsLikeData,
    @Json(name = "pressure") val pressure: Float,
    @Json(name = "humidity") val humidity: Float
)

data class CurrentConditions (
    @Json(name = "city") val city: CityData,
    @Json(name = "cnt") val count: Int,
    @Json(name = "list") val conditions: List<CurrentConditionsData>,
)
