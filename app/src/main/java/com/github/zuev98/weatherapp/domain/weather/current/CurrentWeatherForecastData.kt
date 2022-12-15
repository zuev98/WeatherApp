package com.github.zuev98.weatherapp.domain.weather.current

import androidx.annotation.DrawableRes

data class CurrentWeatherForecastData(
    val time: String,
    @DrawableRes val conditionIcon: Int,
    val tempC: Double
)
