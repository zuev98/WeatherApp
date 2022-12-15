package com.github.zuev98.weatherapp.presentation.ui.dayforecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.zuev98.weatherapp.databinding.FragmentDayForecastListBinding
import com.github.zuev98.weatherapp.presentation.ui.dayforecast.adapter.DayForecastListAdapter
import com.github.zuev98.weatherapp.presentation.ui.dayforecast.vm.DayForecastListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DayForecastListFragment : Fragment() {
    private var _binding: FragmentDayForecastListBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val dayForecastViewModel: DayForecastListViewModel by viewModels()
    private lateinit var adapter: DayForecastListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentDayForecastListBinding.inflate(layoutInflater, container, false)

        bindRV()

        return binding.root
    }

    private fun bindRV() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = DayForecastListAdapter()
        adapter.setOnItemClickListener {
            findNavController().navigate(
                DayForecastListFragmentDirections.showHourForecastList(it)
            )
        }
        binding.recyclerView.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dayForecastViewModel.forecast.observe(
            viewLifecycleOwner
        ) { state ->
            state.data?.let {
                adapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}