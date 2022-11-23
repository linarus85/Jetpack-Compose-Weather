package com.example.aniweather.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.aniweather.data.mappers.toWeatherDaysInfo
import com.example.aniweather.data.mappers.toWeatherInfo
import com.example.aniweather.data.remote.WeatherApi
import com.example.aniweather.domain.repository.WeatherRepository
import com.example.aniweather.domain.util.Resource
import com.example.aniweather.domain.weather.WeatherDaysInfo
import com.example.aniweather.domain.weather.WeatherInfo
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
): WeatherRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long
                ).toWeatherInfo()
            )
        } catch(e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeatherDaysData(lat: Double, long: Double): Resource<WeatherDaysInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherDaysData(
                    lat = lat,
                    long = long
                ).toWeatherDaysInfo()
            )
        } catch(e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}