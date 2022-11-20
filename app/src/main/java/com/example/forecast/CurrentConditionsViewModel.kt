package com.example.forecast

import androidx.lifecycle.ViewModel
import com.example.forecast.service.OpenWeatherMapApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class CurrentConditionsViewModel @Inject constructor(private val api: OpenWeatherMapApi): ViewModel() {
    private val _currentConditions = Channel<CurrentConditions>()

    public val currentConditions: Flow<CurrentConditions> = _currentConditions.receiveAsFlow()

    fun fetchData( lat: Double, lon: Double ) = runBlocking {
        val currentConditions = api.getCurrentConditions(
            lat,
            lon,
            "d9b12b3beed50ad8f61761ed87b3ad76",
            16
        )
        _currentConditions.trySend(currentConditions)
    }
}