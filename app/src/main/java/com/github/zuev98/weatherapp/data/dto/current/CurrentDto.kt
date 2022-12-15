package com.github.zuev98.weatherapp.data.dto.current

import com.github.zuev98.weatherapp.data.dto.condition.ConditionDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentDto(
    @field:Json(name = "last_updated")
    val lastUpdated: String,
    @field:Json(name = "temp_c")
    val tempC: Double,
    @field:Json(name = "is_day")
    val isDay: Int,
    val condition: ConditionDto,
    @field:Json(name = "wind_kph")
    val windSpeed: Double,
    @field:Json(name = "wind_dir")
    val windDir: String,
    @field:Json(name = "pressure_mb")
    val pressureMb: Int,
    @field:Json(name = "precip_mm")
    val precipMM: Double,
    val humidity: Int,
    val cloud: Int,
    @field:Json(name = "feelslike_c")
    val feelsLikeC: Double,
    val uv: Double
)
