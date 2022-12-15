package com.github.zuev98.weatherapp.data.dto.forecast

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastDto(
    @field:Json(name = "forecastday")
    val forecastDay : List<ForecastDayDto>
)
