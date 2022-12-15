package com.github.zuev98.weatherapp.presentation.ui.hourforecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.zuev98.weatherapp.databinding.FragmentHourForecastListBinding
import com.github.zuev98.weatherapp.presentation.ui.hourforecast.adapter.HourForecastListAdapter
import com.github.zuev98.weatherapp.presentation.ui.hourforecast.vm.HourForecastListViewModel
import com.github.zuev98.weatherapp.presentation.ui.hourforecast.vm.HourForecastListViewModelFactory
import com.github.zuev98.weatherapp.presentation.ui.hourforecast.vm.provideFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HourForecastListFragment : Fragment() {
    private var _binding: FragmentHourForecastListBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }
    private val args: HourForecastListFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelAssistedFactory: HourForecastListViewModelFactory
    private val hourForecastViewModel: HourForecastListViewModel by viewModels {
        provideFactory(viewModelAssistedFactory, args.position)
    }
    private lateinit var adapter: HourForecastListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentHourForecastListBinding.inflate(layoutInflater, container, false)

        bindRV()

        return binding.root
    }

    private fun bindRV() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = HourForecastListAdapter()
        binding.recyclerView.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hourForecastViewModel.forecast.observe(
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