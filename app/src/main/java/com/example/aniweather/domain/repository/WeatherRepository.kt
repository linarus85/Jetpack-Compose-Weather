package com.example.aniweather.domain.repository

import com.example.aniweather.domain.util.Resource
import com.example.aniweather.domain.weather.WeatherDaysInfo
import com.example.aniweather.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
    suspend fun getWeatherDaysData(lat: Double, long: Double): Resource<WeatherDaysInfo>
}