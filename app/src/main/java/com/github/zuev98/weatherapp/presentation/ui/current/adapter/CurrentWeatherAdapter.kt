package com.github.zuev98.weatherapp.presentation.ui.current.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.zuev98.weatherapp.R
import com.github.zuev98.weatherapp.databinding.ListItemCurrentForecastBinding
import com.github.zuev98.weatherapp.domain.resources.ResourcesHelper
import com.github.zuev98.weatherapp.domain.weather.current.CurrentWeatherForecastData

class CurrentWeatherAdapter
    : ListAdapter<CurrentWeatherForecastData,
        CurrentWeatherAdapter.CurrentWeatherHolder>(Comparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentWeatherHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemCurrentForecastBinding.inflate(inflater, parent, false)
        return CurrentWeatherHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrentWeatherHolder, position: Int) {
        val forecast = getItem(position)
        holder.bind(forecast)
    }

    inner class CurrentWeatherHolder(
        private val binding: ListItemCurrentForecastBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(forecast: CurrentWeatherForecastData) {
            binding.apply {
                time.text = forecast.time
                conditionImg.setImageResource(forecast.conditionIcon)
                ResourcesHelper.resources?.let {
                    temperature.text = it.getString(R.string.temper_degree, forecast.tempC)
                }
            }
        }
    }

    class Comparator : DiffUtil.ItemCallback<CurrentWeatherForecastData>() {
        override fun areItemsTheSame(
            oldItem: CurrentWeatherForecastData,
            newItem: CurrentWeatherForecastData
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: CurrentWeatherForecastData,
            newItem: CurrentWeatherForecastData
        ): Boolean {
            return oldItem == newItem
        }

    }
}