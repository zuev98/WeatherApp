package com.github.zuev98.weatherapp.domain.usecases

import com.github.zuev98.weatherapp.domain.location.LocationTracker
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLocationUseCase @Inject constructor(
    private val locationTracker: LocationTracker
) {
    suspend fun getLocation(): String? {
        return locationTracker.getCurrentLocation()?.let { location ->
            "${location.latitude},${location.longitude}"
        }
    }
}