package com.github.zuev98.weatherapp.domain.weather.dayforecast

import androidx.annotation.DrawableRes

data class DayForecastData(
    val date: String,
    val maxTempC: Double,
    val minTempC: Double,
    val maxWindSpeed: Double,
    val totalPrecipMM: Double,
    val avgHumidity: Int,
    val chanceOfRain: Int,
    val chanceOfSnow: Int,
    val condition: String,
    @DrawableRes val conditionIcon: Int
)