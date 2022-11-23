package com.example.aniweather.presentation

import com.example.aniweather.domain.weather.WeatherDaysInfo


data class WeatherDaysState(
    val weatherInfo: WeatherDaysInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
