package com.github.zuev98.weatherapp.presentation.ui.dayforecast.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.zuev98.weatherapp.domain.usecases.GetConvertedDataUseCase
import com.github.zuev98.weatherapp.domain.weather.dayforecast.DayForecastData
import com.github.zuev98.weatherapp.presentation.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DayForecastListViewModel @Inject constructor(
    private val getConvertedDataUseCase: GetConvertedDataUseCase
) : ViewModel() {

    private var _forecast = MutableLiveData<State<List<DayForecastData>>>()
    val forecast: LiveData<State<List<DayForecastData>>>
        get() = _forecast

    init {
        viewModelScope.launch {
            _forecast.value = State.onSuccess(getConvertedDataUseCase.dayForecast)
        }
    }
}