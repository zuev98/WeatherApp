package com.github.zuev98.weatherapp.data.exceptions

class LocationException : Exception(message) {

    companion object {
        const val message = "Please check if GPS is enabled"
    }
}