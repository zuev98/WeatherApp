package com.github.zuev98.weatherapp.domain.weather.current

import androidx.annotation.DrawableRes

data class CurrentWeatherData(
    val city: String,
    val lastUpdated: String,
    val tempC: Double,
    val condition: String,
    @DrawableRes val conditionIcon: Int,
    val windSpeed: Double,
    val windDir: String,
    val pressureMb: Int,
    val precipMM: Double,
    val humidity: Int,
    val cloud: Int,
    val feelsLikeC: Double,
    val uv: Double,
    val forecastData: List<CurrentWeatherForecastData>
)
