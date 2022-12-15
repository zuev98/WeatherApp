package com.github.zuev98.weatherapp.data.dto

import com.github.zuev98.weatherapp.data.dto.current.CurrentDto
import com.github.zuev98.weatherapp.data.dto.forecast.ForecastDto
import com.github.zuev98.weatherapp.data.dto.location.LocationDto
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponseDto(
    val location: LocationDto,
    val current: CurrentDto,
    val forecast: ForecastDto
)