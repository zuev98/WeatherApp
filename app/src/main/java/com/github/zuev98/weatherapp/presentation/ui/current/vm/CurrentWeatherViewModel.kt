package com.github.zuev98.weatherapp.presentation.ui.current.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.zuev98.weatherapp.data.exceptions.LocationException
import com.github.zuev98.weatherapp.data.exceptions.NetworkException
import com.github.zuev98.weatherapp.data.exceptions.ResponseException
import com.github.zuev98.weatherapp.domain.usecases.GetConvertedDataUseCase
import com.github.zuev98.weatherapp.domain.usecases.GetLocationUseCase
import com.github.zuev98.weatherapp.domain.weather.current.CurrentWeatherData
import com.github.zuev98.weatherapp.presentation.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val getConvertedDataUseCase: GetConvertedDataUseCase,
    private val getLocationUseCase: GetLocationUseCase
) : ViewModel() {

    var isNotVisible = MutableLiveData(false)
    var isUpdateNeed = MutableLiveData(true)

    private var _currentWeather = MutableLiveData<State<CurrentWeatherData>>()
    val currentWeather: LiveData<State<CurrentWeatherData>>
        get() = _currentWeather

    fun loadWeatherByLocation() {
        viewModelScope.launch {
            _currentWeather.value =
                getLocationUseCase.getLocation()?.let { location ->
                    loadWeather(location)
                } ?: kotlin.run {
                    State.onError(LocationException())
                }
        }
    }

    fun loadWeatherByCity(city: String) {
        viewModelScope.launch {
            _currentWeather.value = loadWeather(city)
        }
    }

    private suspend fun loadWeather(cityOrLocation: String): State<CurrentWeatherData> =
        try {
            State.onSuccess(getConvertedDataUseCase.getCurrentWeatherData(cityOrLocation))
        } catch (networkException: NetworkException) {
            State.onError(networkException)
        }
        catch (responseException: ResponseException) {
            State.onError(responseException)
        }
        catch (exception: Exception) {
            State.onError(exception)
        }
}