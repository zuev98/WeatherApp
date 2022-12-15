package com.github.zuev98.weatherapp.data.exceptions

class ResponseException : Exception(message) {

    companion object {
        const val message = "Please check the city name"
    }
}