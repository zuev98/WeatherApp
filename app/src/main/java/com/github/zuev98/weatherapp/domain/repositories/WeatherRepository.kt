package com.github.zuev98.weatherapp.domain.repositories

import com.github.zuev98.weatherapp.data.dto.WeatherResponseDto

interface WeatherRepository {
    fun getWeather(apiKey: String, cityOrLocation: String, days: Int): WeatherResponseDto
}