package com.github.zuev98.weatherapp.data.dto.forecast

import com.github.zuev98.weatherapp.data.dto.condition.ConditionDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DayDto(
    @field:Json(name = "maxtemp_c")
    val maxTempC: Double,
    @field:Json(name = "mintemp_c")
    val minTempC: Double,
    @field:Json(name = "maxwind_kph")
    val maxWindSpeed: Double,
    @field:Json(name = "totalprecip_mm")
    val totalPrecipMM: Double,
    @field:Json(name = "avghumidity")
    val avgHumidity: Int,
    @field:Json(name = "daily_chance_of_rain")
    val chanceOfRain: Int,
    @field:Json(name = "daily_chance_of_snow")
    val chanceOfSnow: Int,
    val condition: ConditionDto
)
