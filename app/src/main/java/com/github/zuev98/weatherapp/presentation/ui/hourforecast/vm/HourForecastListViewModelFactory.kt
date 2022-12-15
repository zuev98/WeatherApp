package com.github.zuev98.weatherapp.presentation.ui.hourforecast.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.AssistedFactory

@AssistedFactory
interface HourForecastListViewModelFactory {
    fun create(position: Int): HourForecastListViewModel
}

fun provideFactory(
    assistedFactory: HourForecastListViewModelFactory,
    position: Int
) : ViewModelProvider.Factory =
    object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return assistedFactory.create(position) as T
        }
    }