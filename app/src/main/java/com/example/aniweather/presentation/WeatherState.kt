package com.example.aniweather.presentation

import androidx.annotation.StringRes
import com.example.aniweather.domain.weather.WeatherInfo


data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
     val error: String? = null
)
