package com.github.zuev98.weatherapp.data.exceptions

class GeneralException : Exception(message) {

    companion object {
        const val message = "An unexpected error has occurred"
    }
}