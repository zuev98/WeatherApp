package com.github.zuev98.weatherapp.presentation.ui.dayforecast.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.zuev98.weatherapp.R
import com.github.zuev98.weatherapp.databinding.ListItemDayForecastBinding
import com.github.zuev98.weatherapp.domain.resources.ResourcesHelper
import com.github.zuev98.weatherapp.domain.weather.dayforecast.DayForecastData

class DayForecastListAdapter
    : ListAdapter<DayForecastData, DayForecastListAdapter.DayForecastHolder>(Comparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayForecastHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemDayForecastBinding.inflate(inflater, parent, false)
        return DayForecastHolder(binding)
    }

    override fun onBindViewHolder(holder: DayForecastHolder, position: Int) {
        val forecast = getItem(position)
        holder.bind(forecast)
    }

    inner class DayForecastHolder(
        private val binding: ListItemDayForecastBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(forecast: DayForecastData) {
            bindImage()

            binding.apply {
                date.text = forecast.date
                condition.text = forecast.condition
                conditionImg.setImageResource(forecast.conditionIcon)

                ResourcesHelper.resources?.let {
                    minMaxTemper.text = it
                        .getString(R.string.min_max_temper, forecast.minTempC, forecast.maxTempC)
                    windReading.rd.text = it.getString(R.string.max_wind, forecast.maxWindSpeed)
                    precipReading.rd.text = it.getString(R.string.precip, forecast.totalPrecipMM)
                    humidityReading.rd.text = it.getString(R.string.percent, forecast.avgHumidity)
                    chanceOfRainReading.rd.text = it
                        .getString(R.string.percent, forecast.chanceOfRain)
                    chanceOfSnowReading.rd.text = it
                        .getString(R.string.percent, forecast.chanceOfSnow)
                }

                root.setOnClickListener {
                    onItemClickListener?.let { it(bindingAdapterPosition) }
                }
            }
        }

        private fun bindImage() {
            binding.apply {
                windImg.img.setImageResource(R.drawable.wind)
                precipImg.img.setImageResource(R.drawable.precip)
                humidityImg.img.setImageResource(R.drawable.humidity)
                chanceOfRainImg.img.setImageResource(R.drawable.rain)
                chanceOfSnowImg.img.setImageResource(R.drawable.snow)
            }
        }
    }

    private var onItemClickListener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

    class Comparator : DiffUtil.ItemCallback<DayForecastData>() {
        override fun areItemsTheSame(
            oldItem: DayForecastData,
            newItem: DayForecastData
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: DayForecastData,
            newItem: DayForecastData
        ): Boolean {
            return oldItem == newItem
        }
    }
}