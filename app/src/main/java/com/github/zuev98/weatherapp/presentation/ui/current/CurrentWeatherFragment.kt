package com.github.zuev98.weatherapp.presentation.ui.current

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.*
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.zuev98.weatherapp.R
import com.github.zuev98.weatherapp.data.exceptions.GeneralException
import com.github.zuev98.weatherapp.data.exceptions.LocationException
import com.github.zuev98.weatherapp.data.exceptions.NetworkException
import com.github.zuev98.weatherapp.data.exceptions.ResponseException
import com.github.zuev98.weatherapp.databinding.FragmentCurrentWeatherBinding
import com.github.zuev98.weatherapp.domain.weather.current.CurrentWeatherData
import com.github.zuev98.weatherapp.presentation.state.ResponseStatus
import com.github.zuev98.weatherapp.presentation.ui.current.adapter.CurrentWeatherAdapter
import com.github.zuev98.weatherapp.presentation.ui.current.dialogs.DialogManager
import com.github.zuev98.weatherapp.presentation.ui.current.vm.CurrentWeatherViewModel
import com.github.zuev98.weatherapp.presentation.util.APP_PREFERENCES
import com.github.zuev98.weatherapp.presentation.util.APP_PREFERENCES_CITY
import com.github.zuev98.weatherapp.presentation.util.APP_PREFERENCES_FIRST_TIME
import dagger.hilt.android.AndroidEntryPoint

private const val DEFAULT_CITY = "Moscow"

@AndroidEntryPoint
class CurrentWeatherFragment : Fragment() {
    private lateinit var sharedP: SharedPreferences
    private var _binding: FragmentCurrentWeatherBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val currentWeatherViewModel: CurrentWeatherViewModel by viewModels()
    private lateinit var adapter: CurrentWeatherAdapter
    private val geoPermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedP = requireContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentCurrentWeatherBinding.inflate(layoutInflater, container, false)

        bindRV()
        bindImages()
        initListeners()
        initPermissionLauncher()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateWeatherIfNeeded()

        currentWeatherViewModel.currentWeather.observe(viewLifecycleOwner) { state ->
            when (state.responseStatus) {
                ResponseStatus.SUCCESS -> state.data?.let { updateUi(it) }
                ResponseStatus.ERROR -> when (state.exception) {
                    is NetworkException -> showNetworkError(state.exception.message!!)
                    is LocationException -> showLocationError(state.exception.message!!)
                    is ResponseException -> showResponseError(state.exception.message!!)
                    is GeneralException -> showGeneralError(state.exception.message!!)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        //Hide Views if they are empty
        if (currentWeatherViewModel.isNotVisible.value == true) {
            goneViews()
        }
        updateWeatherIfNeeded()
    }

    private fun bindRV() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = CurrentWeatherAdapter()
        binding.recyclerView.adapter = adapter
    }

    private fun bindImages() {
        binding.apply {
            windImg.img.setImageResource(R.drawable.wind)
            pressureImg.img.setImageResource(R.drawable.pressure)
            precipImg.img.setImageResource(R.drawable.precip)
            feelsLikeImg.img.setImageResource(R.drawable.feels_like)
            humidityImg.img.setImageResource(R.drawable.humidity)
            cloudImg.img.setImageResource(R.drawable.cloudy)
            uvImg.img.setImageResource(R.drawable.uv)
        }
    }

    private fun initListeners() {
        binding.apply {
            refreshLayout.setOnRefreshListener {
                if (city.text.isNotBlank())
                    loadWeatherByCity(city.text.toString())
                else
                    loadWeatherByCity()
                refreshLayout.isRefreshing = false
            }

            searchImgBtn.setOnClickListener {
                DialogManager.searchByCityDialog(requireContext(), ::loadWeatherByCity)
            }

            locationImgBtn.setOnClickListener {
                permissionLauncher.launch(geoPermissions)
            }

            retryBtn.setOnClickListener {
                loadWeather()
            }

            forecastBtn.setOnClickListener {
                findNavController().navigate(
                    CurrentWeatherFragmentDirections.showDayForecastList()
                )
            }

            detailsBtn.setOnClickListener {
                if (binding.city.text.isNotBlank()) {
                    val todayPosition = 0
                    findNavController().navigate(
                        CurrentWeatherFragmentDirections.showHourForecastList(todayPosition)
                    )
                }
            }
        }
    }

    private fun initPermissionLauncher() {
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            if (isConnectivityAvailable()) {
                if (it[geoPermissions[0]]!! && it[geoPermissions[1]]!!) {
                    loadWeatherByLocation()
                } else if (sharedP.getBoolean(APP_PREFERENCES_FIRST_TIME, true)) {
                    loadWeatherByCity()
                    notFirstLaunchApp()
                }
            } else {
                showNetworkError()
            }
        }
    }

    private fun isConnectivityAvailable(): Boolean {
        val connectivityManager =
           activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                return when {
                    capabilities.hasTransport(TRANSPORT_WIFI) -> true
                    capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                    capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            }
        }
        return false
    }

    private fun updateWeatherIfNeeded() {
        if (currentWeatherViewModel.isUpdateNeed.value == true) {
            loadWeather()
            currentWeatherViewModel.isUpdateNeed.value = false
        }
    }

    private fun loadWeather() {
        //At the first start try to load the current city otherwise saved
        if (sharedP.getBoolean(APP_PREFERENCES_FIRST_TIME, true)) {
            if (isConnectivityAvailable()) {
                goneViews()
                permissionLauncher.launch(geoPermissions)
            }
            else {
                showNetworkError()
            }
        } else {
            loadWeatherByCity()
        }
    }

    private fun loadWeatherByCity(
        city: String = sharedP.getString(APP_PREFERENCES_CITY, DEFAULT_CITY)!!
    ) {
        if (isConnectivityAvailable()) {
            showLoading()
            currentWeatherViewModel.loadWeatherByCity(city)
        } else {
            showNetworkError()
        }
    }

    private fun loadWeatherByLocation() {
        val locationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            showLoading()
            notFirstLaunchApp()
            currentWeatherViewModel.loadWeatherByLocation()
        } else {
            showLocationError(onCancel = ::notFirstLaunchApp)
        }
    }

    private fun showLoading() {
        Toast.makeText(context, getString(R.string.loading), Toast.LENGTH_SHORT).show()
    }

    private fun notFirstLaunchApp() {
        sharedP.edit().putBoolean(APP_PREFERENCES_FIRST_TIME, false).apply()
    }

    private fun updateUi(data: CurrentWeatherData) {
        showViews()

        binding.apply {
            lastUpdate.text = data.lastUpdated
            city.text = data.city
            temperature.text = getString(R.string.temperature, data.tempC)
            condition.text = data.condition
            conditionImg.setImageResource(data.conditionIcon)

            windReading.rd.text = getString(R.string.wind, data.windDir, data.windSpeed)
            pressureReading.rd.text = getString(R.string.pressure, data.pressureMb)
            precipReading.rd.text = getString(R.string.precip, data.precipMM)
            feelsLikeReading.rd.text = getString(R.string.temperature, data.feelsLikeC)
            humidityReading.rd.text = getString(R.string.percent, data.humidity)
            cloudReading.rd.text = getString(R.string.percent, data.cloud)
            uvReading.rd.text = getString(R.string.uv, data.uv)
        }

        adapter.submitList(data.forecastData)
        scrollToPosition()
    }

    private fun scrollToPosition() {
        val calendar = java.util.Calendar.getInstance()
        var position = calendar.get(java.util.Calendar.HOUR_OF_DAY)
        if (position < 22) position += 2 else if (position == 22) position += 1
        binding.recyclerView.postDelayed(
            { binding.recyclerView.layoutManager?.scrollToPosition(position) },
            35L
        )
    }

    override fun onStop() {
        super.onStop()
        val city = binding.city.text.toString()
        if (city.isNotBlank())
            sharedP
                .edit()
                .putString(APP_PREFERENCES_CITY, city)
                .apply()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val city = binding.city.text.toString()
        if (city.isBlank())
            currentWeatherViewModel.isNotVisible.value = true

        _binding = null
    }

    private fun showViews() {
        binding.apply {
            locationImgBtn.visibility = View.VISIBLE
            searchImgBtn.visibility = View.VISIBLE
            windCv.visibility = View.VISIBLE
            pressureCv.visibility = View.VISIBLE
            precipCv.visibility = View.VISIBLE
            feelsLikeCv.visibility = View.VISIBLE
            humidityCv.visibility = View.VISIBLE
            cloudCv.visibility = View.VISIBLE
            uvCv.visibility = View.VISIBLE
            detailsBtn.visibility = View.VISIBLE
            forecastBtn.visibility = View.VISIBLE

            retryBtn.visibility = View.GONE
        }
    }

    private fun goneViews() {
        if (binding.city.text.isBlank()) {
            binding.apply {
                locationImgBtn.visibility = View.GONE
                searchImgBtn.visibility = View.GONE
                windCv.visibility = View.GONE
                pressureCv.visibility = View.GONE
                precipCv.visibility = View.GONE
                feelsLikeCv.visibility = View.GONE
                humidityCv.visibility = View.GONE
                cloudCv.visibility = View.GONE
                uvCv.visibility = View.GONE
                detailsBtn.visibility = View.GONE
                forecastBtn.visibility = View.GONE

                retryBtn.visibility = View.VISIBLE
            }
        }
    }

    private fun showNetworkError(message: String = getString(R.string.turn_on_internet)) {
        goneViews()
        showError(
            getString(R.string.no_internet),
            message,
            getString(R.string.settings),
            ::launchWirelessSettings
        )
    }

    private fun launchWirelessSettings() {
        startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS))
        currentWeatherViewModel.isUpdateNeed.value = true
    }

    private fun showLocationError(
        message: String = getString(R.string.turn_on_gps),
        onCancel: (() -> Unit)? = null
    ) {
        goneViews()
        showError(
            getString(R.string.no_gps),
            message,
            getString(R.string.settings),
            ::launchLocationSettings,
            onCancel
        )
    }

    private fun launchLocationSettings() {
        startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        if (sharedP.getBoolean(APP_PREFERENCES_FIRST_TIME, true))
            currentWeatherViewModel.isUpdateNeed.value = true
    }

    private fun showResponseError(message: String) {
        showError(getString(R.string.response_failed), message)
    }

    private fun showGeneralError(message: String) {
        showError(getString(R.string.smth_went_wrong), message)
    }

    private fun showError(
        title: String,
        message: String,
        buttonText: String = "",
        onOkay: (() -> Unit)? = null,
        onCancel: (() -> Unit)? = null
    ) {
        DialogManager
            .showErrorDialog(requireContext(), title, message, buttonText, onOkay, onCancel)
    }
}