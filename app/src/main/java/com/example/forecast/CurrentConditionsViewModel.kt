package com.example.forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.forecast.service.OpenWeatherMapApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class CurrentConditionsViewModel @Inject constructor(private val api: OpenWeatherMapApi): ViewModel() {
    private val _currentConditions = MutableLiveData<CurrentConditions>()
    public val currentConditions: LiveData<CurrentConditions> = _currentConditions

    fun fetchData() = runBlocking {
        _currentConditions.value = api.getCurrentConditions("54016", "")
    }
}