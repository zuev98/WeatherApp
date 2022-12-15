package com.github.zuev98.weatherapp.data.api

import com.github.zuev98.weatherapp.data.dto.WeatherResponseDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("forecast.json")
    fun getWeather(
        @Query("key") apiKey: String,
        @Query("q") city: String,
        @Query("days") days: Int
    ) : Call<WeatherResponseDto>
}