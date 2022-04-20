package ru.eruditeonline.app.presentation.ui.country

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentSelectCountryBinding
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.fitTopInsetsWithPadding
import ru.eruditeonline.app.presentation.navigation.observeNavigationCommands
import ru.eruditeonline.app.presentation.ui.base.BaseFragment
import javax.inject.Inject

class SelectCountryFragment : BaseFragment(R.layout.fragment_select_country) {

    companion object {
        const val REQUEST_CODE = "select_country_request_code"
        const val KEY_COUNTRY = "key_country"
    }

    private val binding by viewBinding(FragmentSelectCountryBinding::bind)
    private val viewModel: SelectCountryViewModel by appViewModels()

    @Inject lateinit var countriesAdapter: CountriesAdapter

    override fun callOperations() {
        viewModel.loadCountries()
    }

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        toolbar.fitTopInsetsWithPadding()
        toolbar.setNavigationOnClickListener {
            viewModel.navigateBack()
        }
        setupRecyclerView()
        stateViewFlipper.setRetryMethod { viewModel.loadCountries() }
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
        countriesLiveData.observe { state ->
            binding.stateViewFlipper.setState(state)
            state.doOnSuccess { countries ->
                countriesAdapter.submitList(countries)
            }
        }
    }

    private fun setupRecyclerView() = with(binding.recyclerView) {
        countriesAdapter.onItemClick = { country ->
            setFragmentResult(REQUEST_CODE, bundleOf(KEY_COUNTRY to country))
            viewModel.navigateBack()
        }
        adapter = countriesAdapter
    }
}
