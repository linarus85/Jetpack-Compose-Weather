package com.example.aniweather.data.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aniweather.domain.location.LocationTracker
import com.example.aniweather.domain.repository.WeatherRepository
import com.example.aniweather.domain.util.Resource
import com.example.aniweather.presentation.WeatherDaysState
import com.example.aniweather.presentation.WeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDaysViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
) : ViewModel() {

    var stateDay by mutableStateOf(WeatherDaysState())

    fun loadWeatherDaysInfo() {
        viewModelScope.launch {
            stateDay = stateDay.copy(
                isLoading = true,
                error = null
            )
            locationTracker.getCurrentLocation()?.let { location ->
                when (val result =
                    repository.getWeatherDaysData(location.latitude, location.longitude)) {
                    is Resource.Success -> {
                        stateDay = stateDay.copy(
                            weatherInfo = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                    is Resource.Error -> {
                        stateDay = stateDay.copy(
                            weatherInfo = null,
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            } ?: kotlin.run {
                stateDay = stateDay.copy(
                    isLoading = false,
                    error = "GPS & INTERNET ?"
                )
            }
        }
    }
}