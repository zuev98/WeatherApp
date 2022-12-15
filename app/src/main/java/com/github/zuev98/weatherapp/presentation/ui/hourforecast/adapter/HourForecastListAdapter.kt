package com.github.zuev98.weatherapp.presentation.ui.hourforecast.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.zuev98.weatherapp.R
import com.github.zuev98.weatherapp.databinding.ListItemHourForecastBinding
import com.github.zuev98.weatherapp.domain.resources.ResourcesHelper
import com.github.zuev98.weatherapp.domain.weather.hourforecast.HourForecastData

class HourForecastListAdapter
    : ListAdapter<HourForecastData, HourForecastListAdapter.HourForecastHolder>(Comparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourForecastHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemHourForecastBinding.inflate(inflater, parent, false)
        return HourForecastHolder(binding)
    }

    override fun onBindViewHolder(holder: HourForecastHolder, position: Int) {
        val forecast = getItem(position)
        holder.bind(forecast)
    }

    inner class HourForecastHolder(
        private val binding: ListItemHourForecastBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(forecast: HourForecastData) {
            bindImage()

            binding.apply {
                time.text = forecast.time
                condition.text = forecast.condition
                conditionImg.setImageResource(forecast.conditionIcon)

                ResourcesHelper.resources?.let {
                    temperature.text = it.getString(R.string.temperature, forecast.tempC)
                    windReading.rd.text =
                        it.getString(R.string.wind, forecast.windDir, forecast.windSpeed)
                    feelsLikeReading.rd.text =
                        it.getString(R.string.temperature, forecast.feelsLikeC)
                    humidityReading.rd.text = it.getString(R.string.percent, forecast.humidity)
                    chanceOfRainReading.rd.text = it
                        .getString(R.string.percent, forecast.chanceOfRain)
                    chanceOfSnowReading.rd.text = it
                        .getString(R.string.percent, forecast.chanceOfSnow)
                }
            }
        }

        private fun bindImage() {
            binding.apply {
                windImg.img.setImageResource(R.drawable.wind)
                feelsLikeImg.img.setImageResource(R.drawable.feels_like)
                humidityImg.img.setImageResource(R.drawable.humidity)
                chanceOfRainImg.img.setImageResource(R.drawable.rain)
                chanceOfSnowImg.img.setImageResource(R.drawable.snow)
            }
        }
    }

    class Comparator : DiffUtil.ItemCallback<HourForecastData>() {
        override fun areItemsTheSame(
            oldItem: HourForecastData,
            newItem: HourForecastData
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: HourForecastData,
            newItem: HourForecastData
        ): Boolean {
            return oldItem == newItem
        }
    }
}