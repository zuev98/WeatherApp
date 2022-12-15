package com.github.zuev98.weatherapp.presentation.state

data class State<T>(val responseStatus: ResponseStatus, val data: T?, val exception: Exception?) {

    companion object {
        fun <T> onSuccess(data: T?): State<T> =
            State(ResponseStatus.SUCCESS, data, null)

        fun <T> onError(exception: Exception?): State<T> =
            State(ResponseStatus.ERROR, null, exception)
    }
}

enum class ResponseStatus {
    SUCCESS,
    ERROR
}