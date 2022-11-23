package com.example.aniweather.data.remote

import com.squareup.moshi.Json

data class WeatherDataDaysDto(
    val time: List<String>,
    @field:Json(name = "temperature_2m_max")
    val temperaturesMax: List<Double>,
    @field:Json(name = "temperature_2m_min")
    val temperaturesMin: List<Double>,
    @field:Json(name = "weathercode")
    val weatherCodes: List<Int>

)