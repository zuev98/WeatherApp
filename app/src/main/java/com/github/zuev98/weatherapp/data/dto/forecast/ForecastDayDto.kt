package com.github.zuev98.weatherapp.data.dto.forecast

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastDayDto(
    val date: String,
    val day: DayDto,
    val hour: List<HourDto>
)
