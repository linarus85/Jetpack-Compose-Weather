package com.example.aniweather

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.aniweather.data.viewModel.WeatherDaysViewModel
import com.example.aniweather.presentation.WeatherCard
import com.example.aniweather.presentation.WeatherDaysForecast
import com.example.aniweather.presentation.WeatherForecast
import com.example.aniweather.data.viewModel.WeatherViewModel
import com.example.aniweather.ui.theme.AniWeatherTheme
import com.example.aniweather.ui.theme.DarkBlue
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalPermissionsApi
class MainActivity : ComponentActivity() {
    private val viewModel: WeatherViewModel by viewModels()
    private val viewModelDays: WeatherDaysViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.loadWeatherInfo()
            viewModelDays.loadWeatherDaysInfo()
        }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )
        setContent {
            AniWeatherTheme {
                this.window.statusBarColor = ContextCompat.getColor(this,R.color.dark_blue)
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(DarkBlue)
                            .verticalScroll(ScrollState(0))
                    ) {
                        WeatherCard(
                            state = viewModel.state,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        WeatherForecast(state = viewModel.state)
                        Spacer(modifier = Modifier.height(16.dp))
                        WeatherDaysForecast(state = viewModelDays.stateDay)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    if (viewModel.state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    if (viewModelDays.stateDay.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    viewModel.state.error?.let { error ->
                        Card(
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .wrapContentSize(Alignment.Center)
                                .padding(16.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.sorry),
                                contentDescription = null,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Text(
                                text = error,
                                color = Color.Red,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .wrapContentSize(Alignment.Center)
                                    .padding(16.dp)
                            )
                        }
                    }
                    viewModelDays.stateDay.error?.let { error ->
                        Card(
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .wrapContentSize(Alignment.Center)
                                .padding(16.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.sorry),
                                contentDescription = null,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Text(
                                text = error,
                                color = Color.Red,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .wrapContentSize(Alignment.Center)
                                    .padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}


