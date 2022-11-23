package com.example.aniweather.data.remote

import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {

    @GET("v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl")
    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    ): WeatherDto

    @GET("v1/forecast?daily=weathercode,temperature_2m_max,temperature_2m_min&timezone=auto")
    suspend fun getWeatherDaysData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    ): WeatherDaysDto

}