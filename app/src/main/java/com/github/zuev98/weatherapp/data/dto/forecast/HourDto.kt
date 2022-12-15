package com.github.zuev98.weatherapp.data.dto.forecast

import com.github.zuev98.weatherapp.data.dto.condition.ConditionDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HourDto(
    val time: String,
    @field:Json(name = "temp_c")
    val tempC: Double,
    @field:Json(name = "is_day")
    val isDay: Int,
    val condition: ConditionDto,
    @field:Json(name = "wind_kph")
    val windSpeed: Double,
    @field:Json(name = "wind_dir")
    val windDir: String,
    @field:Json(name = "feelslike_c")
    val feelsLikeC: Double,
    @field:Json(name = "chance_of_rain")
    val chanceOfRain: Int,
    @field:Json(name = "chance_of_snow")
    val chanceOfSnow: Int,
    val humidity: Int
)
