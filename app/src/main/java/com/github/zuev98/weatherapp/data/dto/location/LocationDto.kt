package com.github.zuev98.weatherapp.data.dto.location

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocationDto(
    @field:Json(name = "name")
    val city: String
)
