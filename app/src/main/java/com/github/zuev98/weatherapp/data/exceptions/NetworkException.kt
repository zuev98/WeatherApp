package com.github.zuev98.weatherapp.data.exceptions

class NetworkException : Exception(message) {

    companion object {
        const val message = "Please check your Internet connection"
    }
}