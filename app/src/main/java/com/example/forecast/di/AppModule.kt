package com.example.forecast.di

import com.example.forecast.service.OpenWeatherMapApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder

@Module
@InstallIn(ActivityComponent::class, ViewModelComponent::class)
class AppModule {

    @Provides
    fun providesRetrofit(): Retrofit {
        return Builder()
            .baseUrl("https://api.openweathermap.org/")
            .build()
    }

    @Provides
    fun providesOpenWeatherMapApi(retrofit: Retrofit): OpenWeatherMapApi {
        return retrofit.create(OpenWeatherMapApi::class.java)
    }
}