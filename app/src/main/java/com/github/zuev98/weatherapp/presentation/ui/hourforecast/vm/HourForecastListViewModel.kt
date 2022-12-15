package com.github.zuev98.weatherapp.presentation.ui.hourforecast.vm

import androidx.lifecycle.*
import com.github.zuev98.weatherapp.domain.usecases.GetConvertedDataUseCase
import com.github.zuev98.weatherapp.domain.weather.hourforecast.HourForecastData
import com.github.zuev98.weatherapp.presentation.state.State
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class HourForecastListViewModel @AssistedInject constructor(
    private val getConvertedDataUseCase: GetConvertedDataUseCase,
    @Assisted position: Int
) : ViewModel() {

    private val _forecast = MutableLiveData<State<List<HourForecastData>>>()
    val forecast: LiveData<State<List<HourForecastData>>>
        get() = _forecast

    init {
        viewModelScope.launch {
            _forecast.value = State.onSuccess(getConvertedDataUseCase.hourForecast[position])
        }
    }
}