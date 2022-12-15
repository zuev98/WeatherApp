package com.github.zuev98.weatherapp.domain.usecases

import com.github.zuev98.weatherapp.BuildConfig
import com.github.zuev98.weatherapp.data.dto.WeatherResponseDto
import com.github.zuev98.weatherapp.data.dto.forecast.ForecastDayDto
import com.github.zuev98.weatherapp.data.dto.forecast.HourDto
import com.github.zuev98.weatherapp.data.exceptions.GeneralException
import com.github.zuev98.weatherapp.data.exceptions.NetworkException
import com.github.zuev98.weatherapp.data.exceptions.ResponseException
import com.github.zuev98.weatherapp.domain.weather.current.CurrentWeatherData
import com.github.zuev98.weatherapp.domain.weather.dayforecast.DayForecastData
import com.github.zuev98.weatherapp.domain.weather.conditioninfo.WeatherConditionInfo
import com.github.zuev98.weatherapp.domain.weather.current.CurrentWeatherForecastData
import com.github.zuev98.weatherapp.domain.weather.hourforecast.HourForecastData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

private const val DAYS = 7

@Singleton
class GetConvertedDataUseCase @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) {
    private val timeFormat = SimpleDateFormat("HH:mm", Locale.UK)
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.UK)

    private var current: CurrentWeatherData? = null
    val dayForecast = mutableListOf<DayForecastData>()
    val hourForecast = mutableListOf<MutableList<HourForecastData>>()

    suspend fun getCurrentWeatherData(cityOrLocation: String): CurrentWeatherData {
        //If the forecast is outdated, request data from the server
        if (current == null || current?.city != cityOrLocation || isOutdated()) {
            try {
                val weather = getWeather(cityOrLocation)
                initCurrent(weather)
                initDayForecast(weather.forecast.forecastDay)
                initHourForecast(weather.forecast.forecastDay)
                initCurrentForecast(weather.forecast.forecastDay[0].hour)
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

        return current as CurrentWeatherData
    }

    //The forecast is updated every 15 minutes (0, 15, 30, 45 minutes of the corresponding hour)
    //If the forecast data is outdated, return true, otherwise false
    private fun isOutdated(): Boolean {
        val minute = SimpleDateFormat("mm", Locale.UK)
        val currentMin = minute.format(Date())
        val updatedMin = minute.format(
            current?.lastUpdated?.let {
                dateFormat.parse(it)
            }!!
        )

        if ((currentMin in "00".."14" && updatedMin in "15".."45") ||
            (currentMin in "15".."29" && (updatedMin == "00" || updatedMin in "30".."45")) ||
            (currentMin in "30".."44" && (updatedMin in "00".."15" || updatedMin == "45")) ||
            (currentMin in "45".."59" && updatedMin in "00".."15")) {

            return true
        }

        return false
    }

    private suspend fun getWeather(cityOrLocation: String): WeatherResponseDto =
        withContext(Dispatchers.IO) {
            try {
                getWeatherUseCase.getWeather(BuildConfig.API_KEY, cityOrLocation, DAYS)
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

    private fun initCurrent(weather: WeatherResponseDto) {
        val conditionInfo = WeatherConditionInfo
            .getWeatherConditionInfo(weather.current.condition.code, weather.current.isDay)
        current = CurrentWeatherData(
            city = weather.location.city,
            lastUpdated = weather.current.lastUpdated,
            tempC = weather.current.tempC,
            condition = conditionInfo.condition,
            conditionIcon = conditionInfo.iconRes,
            windSpeed = weather.current.windSpeed,
            windDir = weather.current.windDir,
            pressureMb = weather.current.pressureMb,
            precipMM = weather.current.precipMM,
            humidity = weather.current.humidity,
            cloud = weather.current.cloud,
            feelsLikeC = weather.current.feelsLikeC,
            uv = weather.current.uv,
            forecastData = initCurrentForecast(weather.forecast.forecastDay[0].hour)
        )
    }

    private fun initCurrentForecast(hourForecast: List<HourDto>): List<CurrentWeatherForecastData> {
        val currentForecast = mutableListOf<CurrentWeatherForecastData>()
        for (hour in hourForecast) {
            val conditionInfo = WeatherConditionInfo
                .getWeatherConditionInfo(hour.condition.code, hour.isDay)
            currentForecast.add(
                CurrentWeatherForecastData(
                    time = timeFormat.format(dateFormat.parse(hour.time)!!),
                    conditionIcon = conditionInfo.iconRes,
                    tempC = hour.tempC
                )
            )
        }

        return currentForecast
    }

    private fun initDayForecast(weatherForecast: List<ForecastDayDto>) {
        dayForecast.clear()
        for (forecast in weatherForecast) {
            val conditionInfo = WeatherConditionInfo
                .getWeatherConditionInfo(forecast.day.condition.code)
            dayForecast.add(
                DayForecastData(
                    date = forecast.date,
                    maxTempC = forecast.day.maxTempC,
                    minTempC = forecast.day.minTempC,
                    maxWindSpeed = forecast.day.maxWindSpeed,
                    totalPrecipMM = forecast.day.totalPrecipMM,
                    avgHumidity = forecast.day.avgHumidity,
                    chanceOfRain = forecast.day.chanceOfRain,
                    chanceOfSnow = forecast.day.chanceOfSnow,
                    condition = conditionInfo.condition,
                    conditionIcon = conditionInfo.iconRes
                )
            )
        }
    }

    private fun initHourForecast(weatherForecast: List<ForecastDayDto>) {
        hourForecast.clear()
        weatherForecast.forEachIndexed { i, forecast ->
            hourForecast.add(mutableListOf())
            for (hour in forecast.hour) {
                val conditionInfo = WeatherConditionInfo
                    .getWeatherConditionInfo(hour.condition.code, hour.isDay)
                hourForecast[i].add(
                    HourForecastData(
                        time = timeFormat.format(dateFormat.parse(hour.time)!!),
                        tempC = hour.tempC,
                        isDay = hour.isDay,
                        condition = conditionInfo.condition,
                        conditionIcon = conditionInfo.iconRes,
                        windSpeed = hour.windSpeed,
                        windDir = hour.windDir,
                        feelsLikeC = hour.feelsLikeC,
                        chanceOfRain = hour.chanceOfRain,
                        chanceOfSnow = hour.chanceOfSnow,
                        humidity = hour.humidity
                    )
                )
            }
        }
    }
}