package ru.eruditeonline.app.presentation.ui.competition.filter

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentCompetitionFilterBinding
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.fitTopInsetsWithPadding
import ru.eruditeonline.app.presentation.navigation.observeNavigationCommands
import ru.eruditeonline.app.presentation.ui.base.BaseFragment
import ru.eruditeonline.app.presentation.ui.competition.filter.model.FilterGroup
import ru.eruditeonline.architecture.presentation.model.LoadableState
import javax.inject.Inject

private val TITLES = arrayOf(R.string.filter_ages_title, R.string.filter_subjects_title)

class CompetitionFilterFragment : BaseFragment(R.layout.fragment_competition_filter) {

    companion object {
        const val FILTER_REQUEST_CODE = "filter_request"
        const val FILTER_KEY = "filter_key"
    }

    private val binding by viewBinding(FragmentCompetitionFilterBinding::bind)
    private val viewModel: CompetitionFilterViewModel by appViewModels()
    private val args: CompetitionFilterFragmentArgs by navArgs()

    @Inject lateinit var groupsAdapter: FilterGroupsAdapter

    override fun callOperations() {
        viewModel.initFilters(args.filters)
    }

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        toolbar.fitTopInsetsWithPadding()
        toolbar.setNavigationOnClickListener {
            viewModel.navigateBack()
        }
        toolbar.menu.findItem(R.id.reset).setOnMenuItemClickListener {
            viewModel.resetFilters()
            true
        }
        setupViewPager()
        setupApplyButton()
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
        filtersLiveData.observe { groups ->
            bindGroups(groups)
        }
        applyFilterLiveEvent.observe { filterRequest ->
            setFragmentResult(FILTER_REQUEST_CODE, bundleOf(FILTER_KEY to filterRequest))
            navigateBack()
        }
    }

    private fun setupViewPager() = with(binding) {
        viewPager.offscreenPageLimit = 2
        viewPager.adapter = groupsAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getString(TITLES[position])
        }.attach()
    }

    private fun bindGroups(groups: List<FilterGroup>) = with(binding) {
        groupsAdapter.submitList(groups) {
            stateViewFlipper.setState(LoadableState.Success(Unit))
            tabLayout.isVisible = true
        }
    }

    private fun setupApplyButton() {
        binding.buttonApply.setOnClickListener {
            viewModel.applyFilter(groupsAdapter.currentList)
        }
    }
}
