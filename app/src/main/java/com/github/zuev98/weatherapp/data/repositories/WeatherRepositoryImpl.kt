package com.github.zuev98.weatherapp.data.repositories

import android.util.Log
import com.github.zuev98.weatherapp.data.api.WeatherApiService
import com.github.zuev98.weatherapp.data.dto.WeatherResponseDto
import com.github.zuev98.weatherapp.data.exceptions.GeneralException
import com.github.zuev98.weatherapp.data.exceptions.NetworkException
import com.github.zuev98.weatherapp.data.exceptions.ResponseException
import com.github.zuev98.weatherapp.domain.repositories.WeatherRepository
import java.io.IOException
import javax.inject.Inject

private const val TAG = "weatherRepositoryImpl"

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApiService
) : WeatherRepository {

    override fun getWeather(apiKey: String, cityOrLocation: String, days: Int): WeatherResponseDto {
        val response = try {
            api.getWeather(apiKey, cityOrLocation, days).execute()
        } catch (e: IOException) {
            Log.w(TAG, "An unexpected error has occurred, throwing a NetworkException ")
            throw NetworkException()
        }

        if (!response.isSuccessful) {
            Log.w(TAG, "A response error occurred, throwing a ResponseException " +
                        response.errorBody())
            throw ResponseException()
        }

        val responseBody = response.body()
        if (responseBody == null) {
            Log.w(TAG, "An unexpected error has occurred, throwing a GeneralException " +
                        response.errorBody())
            throw GeneralException()
        }

        return responseBody
    }
}