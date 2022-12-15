package com.github.zuev98.weatherapp.domain.weather.hourforecast

import androidx.annotation.DrawableRes

data class HourForecastData(
    val time: String,
    val tempC: Double,
    val isDay: Int,
    val condition: String,
    @DrawableRes val conditionIcon: Int,
    val windSpeed: Double,
    val windDir: String,
    val feelsLikeC: Double,
    val chanceOfRain: Int,
    val chanceOfSnow: Int,
    val humidity: Int
)
