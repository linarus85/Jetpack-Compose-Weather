package com.example.aniweather.domain.weather

data class WeatherDaysInfo(
    val weatherDataPerDay: Map<Int, List<WeatherDaysData>>,
    val currentWeatherData: WeatherDaysData?
)
