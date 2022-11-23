package com.example.aniweather.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.aniweather.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherCard(
    state: WeatherState,
    modifier: Modifier = Modifier
) {
    state.weatherInfo?.currentWeatherData?.let { data ->
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = modifier.padding(16.dp),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Image(
                painter = painterResource(id = data.weatherType.imageBackground),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = data.time.format(DateTimeFormatter.ofPattern("HH:mm")),
                    modifier = Modifier.align(Alignment.End),
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "${data.temperatureCelsius}°C",
                    fontSize = 50.sp,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(data.weatherType.weatherDesc),
                    fontSize = 20.sp,
                )
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherDataDisplay(
                        value = data.pressure.roundToInt(),
                        unit = "hpa",
                        icon = ImageVector.vectorResource(id = R.drawable.ic_pressure),
                        iconTint = Color.Gray,
                    )
                    WeatherDataDisplay(
                        value = data.humidity.roundToInt(),
                        unit = "%",
                        icon = ImageVector.vectorResource(id = R.drawable.ic_drop),
                        iconTint = Color.Gray,
                    )
                    WeatherDataDisplay(
                        value = data.windSpeed.roundToInt(),
                        unit = "km/h",
                        icon = ImageVector.vectorResource(id = R.drawable.ic_wind),
                        iconTint = Color.Gray,
                    )
                }
            }
        }
    }
}