package com.github.zuev98.weatherapp.data.dto.condition

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConditionDto(
    val code: Int
)
