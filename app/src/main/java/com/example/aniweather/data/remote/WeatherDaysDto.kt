package com.example.aniweather.data.remote

import com.squareup.moshi.Json

data class WeatherDaysDto(
    @field:Json(name = "daily")
    val weatherData: WeatherDataDaysDto
)