package com.github.zuev98.weatherapp.domain.usecases

import com.github.zuev98.weatherapp.data.dto.WeatherResponseDto
import com.github.zuev98.weatherapp.data.exceptions.GeneralException
import com.github.zuev98.weatherapp.data.exceptions.NetworkException
import com.github.zuev98.weatherapp.data.exceptions.ResponseException
import com.github.zuev98.weatherapp.domain.repositories.WeatherRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    fun getWeather(apiKey: String, cityOrLocation: String, days: Int): WeatherResponseDto {
        try {
            return repository.getWeather(apiKey, cityOrLocation, days)
        } catch (networkException: NetworkException) {
            throw networkException
        }
        catch (responseException: ResponseException) {
            throw responseException
        }
        catch (exception: Exception) {
            throw GeneralException()
        }
    }
}