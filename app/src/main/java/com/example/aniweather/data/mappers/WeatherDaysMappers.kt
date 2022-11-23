package com.example.aniweather.data.mappers

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.aniweather.data.remote.WeatherDataDaysDto
import com.example.aniweather.data.remote.WeatherDataDto
import com.example.aniweather.data.remote.WeatherDaysDto
import com.example.aniweather.data.remote.WeatherDto
import com.example.aniweather.domain.weather.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.logging.Level.parse


private data class IndexedWeatherDataDays(
    val index: Int,
    val data: WeatherDaysData
)

@SuppressLint("SimpleDateFormat")
@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDataDaysDto.toWeatherDataDaysMap(): Map<Int, List<WeatherDaysData>> {
    return time.mapIndexed { index, time ->
        val temperatureMax = temperaturesMax[index]
        val temperatureMin = temperaturesMin[index]
        val weatherCode = weatherCodes[index]
        IndexedWeatherDataDays(
            index = index,
            data = WeatherDaysData(
                time = LocalDate.parse(time,DateTimeFormatter.ISO_DATE),
                temperatureMax = temperatureMax,
                temperatureMin = temperatureMin,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index / 7
    }.mapValues { map ->
        map.value.map { it.data }
    }
}

@SuppressLint("SimpleDateFormat")
@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDaysDto.toWeatherDaysInfo(): WeatherDaysInfo {
    val weatherDataMap = weatherData.toWeatherDataDaysMap()
    val now = LocalDate.now()
    val weatherData = weatherDataMap[0]?.find {
        val day = now.dayOfMonth
        it.time.dayOfMonth == day
    }
    return WeatherDaysInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = weatherData
    )
}