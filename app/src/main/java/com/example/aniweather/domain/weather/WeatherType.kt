package com.example.aniweather.domain.weather

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.aniweather.R


sealed class WeatherType(
    @StringRes val weatherDesc: Int,
    @DrawableRes val iconRes: Int,
    @DrawableRes val imageBackground: Int = 0
) {
    object ClearSky : WeatherType(
        weatherDesc = R.string.clear_sky,
        iconRes = R.drawable.ic_sunny,
        imageBackground = R.drawable.sunny
    )

    object MainlyClear : WeatherType(
        weatherDesc = R.string.mainly_clear,
        iconRes = R.drawable.ic_cloudy,
        imageBackground = R.drawable.mainly_clear
    )

    object PartlyCloudy : WeatherType(
        weatherDesc = R.string.partly_cloudy,
        iconRes = R.drawable.ic_cloudy,
        imageBackground = R.drawable.partly_cloudy
    )

    object Overcast : WeatherType(
        weatherDesc = R.string.overcast,
        iconRes = R.drawable.ic_cloudy,
        imageBackground = R.drawable.overcast
    )

    object Foggy : WeatherType(
        weatherDesc = R.string.foggy,
        iconRes = R.drawable.ic_very_cloudy,
        imageBackground = R.drawable.foggy
    )

    object DepositingRimeFog : WeatherType(
        weatherDesc = R.string.images_girl,
        iconRes = R.drawable.ic_very_cloudy,
        imageBackground = R.drawable.images_girl
    )

    object LightDrizzle : WeatherType(
        weatherDesc = R.string.light_drizzle,
        iconRes = R.drawable.ic_rainshower,
        imageBackground = R.drawable.light_drizzle
    )

    object ModerateDrizzle : WeatherType(
        weatherDesc = R.string.moderate_drizzle,
        iconRes = R.drawable.ic_rainshower,
        imageBackground = R.drawable.moderate_drizzle
    )

    object DenseDrizzle : WeatherType(
        weatherDesc = R.string.dense_drizzle,
        iconRes = R.drawable.ic_rainshower,
        imageBackground = R.drawable.dense_drizzle
    )

    object LightFreezingDrizzle : WeatherType(
        weatherDesc = R.string.slight_freezing_drizzle,
        iconRes = R.drawable.ic_snowyrainy,
        imageBackground = R.drawable.slight_freezing_drizzle
    )

    object DenseFreezingDrizzle : WeatherType(
        weatherDesc = R.string.dense_freezing_drizzle,
        iconRes = R.drawable.ic_snowyrainy,
        imageBackground = R.drawable.dense_freezing_drizzle
    )

    object SlightRain : WeatherType(
        weatherDesc = R.string.slight_rain,
        iconRes = R.drawable.ic_rainy,
        imageBackground = R.drawable.slight_rain
    )

    object ModerateRain : WeatherType(
        weatherDesc = R.string.rainy,
        iconRes = R.drawable.ic_rainy,
        imageBackground = R.drawable.rainy
    )

    object HeavyRain : WeatherType(
        weatherDesc = R.string.heavy_rain,
        iconRes = R.drawable.ic_rainy,
        imageBackground = R.drawable.heavy_rain
    )

    object HeavyFreezingRain : WeatherType(
        weatherDesc = R.string.heavy_freezing_rain,
        iconRes = R.drawable.ic_snowyrainy,
        imageBackground = R.drawable.heavy_freezing_rain
    )

    object SlightSnowFall : WeatherType(
        weatherDesc = R.string.slight_snow_fall,
        iconRes = R.drawable.ic_snowy,
        imageBackground = R.drawable.slight_snow_fall
    )

    object ModerateSnowFall : WeatherType(
        weatherDesc = R.string.moderate_snow_fall,
        iconRes = R.drawable.ic_heavysnow,
        imageBackground = R.drawable.moderate_snow_fall
    )

    object HeavySnowFall : WeatherType(
        weatherDesc = R.string.heavy_snow_fall,
        iconRes = R.drawable.ic_heavysnow,
        imageBackground = R.drawable.heavy_snow_fall
    )

    object SnowGrains : WeatherType(
        weatherDesc = R.string.snow_grains,
        iconRes = R.drawable.ic_heavysnow,
        imageBackground = R.drawable.snow_grains
    )

    object SlightRainShowers : WeatherType(
        weatherDesc = R.string.slight_rain_showers,
        iconRes = R.drawable.ic_rainshower,
        imageBackground = R.drawable.slight_rain_showers
    )

    object ModerateRainShowers : WeatherType(
        weatherDesc = R.string.moderate_rain_showers,
        iconRes = R.drawable.ic_rainshower,
        imageBackground = R.drawable.moderate_rain_showers
    )

    object ViolentRainShowers : WeatherType(
        weatherDesc = R.string.violent_rain_showers,
        iconRes = R.drawable.ic_rainshower,
        imageBackground = R.drawable.violent_rain_showers
    )

    object SlightSnowShowers : WeatherType(
        weatherDesc = +R.string.light_snow_showers,
        iconRes = R.drawable.ic_snowy,
        imageBackground = R.drawable.light_snow_showers
    )

    object HeavySnowShowers : WeatherType(
        weatherDesc = R.string.heavy_snow_showers,
        iconRes = R.drawable.ic_snowy,
        imageBackground = R.drawable.heavy_snow_showers
    )

    object ModerateThunderstorm : WeatherType(
        weatherDesc = R.string.moderate_thunderstorm,
        iconRes = R.drawable.ic_thunder,
        imageBackground = R.drawable.moderate_thunderstorm
    )

    object SlightHailThunderstorm : WeatherType(
        weatherDesc = R.string.thunderstorm_with_slight_hail,
        iconRes = R.drawable.ic_rainythunder,
        imageBackground = R.drawable.thunderstorm_with_slight_hail
    )

    object HeavyHailThunderstorm : WeatherType(
        weatherDesc = R.string.thunderstorm_with_heavy_hail,
        iconRes = R.drawable.ic_rainythunder,
        imageBackground = R.drawable.thunderstorm_with_heavy_hail
    )

    companion object {
        fun fromWMO(code: Int): WeatherType {
            return when (code) {
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloudy
                3 -> Overcast
                45 -> Foggy
                48 -> DepositingRimeFog
                51 -> LightDrizzle
                53 -> ModerateDrizzle
                55 -> DenseDrizzle
                56 -> LightFreezingDrizzle
                57 -> DenseFreezingDrizzle
                61 -> SlightRain
                63 -> ModerateRain
                65 -> HeavyRain
                66 -> LightFreezingDrizzle
                67 -> HeavyFreezingRain
                71 -> SlightSnowFall
                73 -> ModerateSnowFall
                75 -> HeavySnowFall
                77 -> SnowGrains
                80 -> SlightRainShowers
                81 -> ModerateRainShowers
                82 -> ViolentRainShowers
                85 -> SlightSnowShowers
                86 -> HeavySnowShowers
                95 -> ModerateThunderstorm
                96 -> SlightHailThunderstorm
                99 -> HeavyHailThunderstorm
                else -> ClearSky
            }
        }
    }
}