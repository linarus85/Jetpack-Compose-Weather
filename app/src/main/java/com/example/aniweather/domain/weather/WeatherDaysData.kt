package com.example.aniweather.domain.weather

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime

data class WeatherDaysData(
    val time: LocalDate,
    val temperatureMax: Double,
    val temperatureMin: Double,
    val weatherType: WeatherType
)
