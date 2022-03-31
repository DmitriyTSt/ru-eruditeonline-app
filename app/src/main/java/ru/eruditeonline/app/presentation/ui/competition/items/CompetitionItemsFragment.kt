package ru.eruditeonline.app.presentation.ui.competition.items

import android.os.Bundle
import android.view.View
import androidx.core.view.updatePadding
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentCompetitionItemsBinding
import ru.eruditeonline.app.presentation.extension.addDefaultGridSpaceItemDecoration
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.disableChangeAnimations
import ru.eruditeonline.app.presentation.extension.fitTopInsetsWithPadding
import ru.eruditeonline.app.presentation.paging.PagingLoadStateAdapter
import ru.eruditeonline.app.presentation.ui.base.BaseFragment
import ru.eruditeonline.app.presentation.ui.competition.items.adapter.CompetitionItemsAdapter
import javax.inject.Inject

class CompetitionItemsFragment : BaseFragment(R.layout.fragment_competition_items) {

    override val showBottomNavigationView: Boolean = true

    private val binding by viewBinding(FragmentCompetitionItemsBinding::bind)
    private val viewModel: CompetitionItemsViewModel by appViewModels()

    private val competitionItemsBottomPadding by lazy { resources.getDimensionPixelSize(R.dimen.padding_16) }

    @Inject lateinit var itemsAdapter: CompetitionItemsAdapter

    override fun callOperations() {
        viewModel.loadCompetitions(null)
    }

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        toolbar.fitTopInsetsWithPadding()
        setupList()
        Unit
    }

    override fun onBindViewModel() = with(viewModel) {
        pagingStateLiveData.observe { state ->
            binding.stateViewFlipper.setState(state)
        }
        pagingDataLiveData.observe { data ->
            itemsAdapter.submitData(lifecycle, data)
        }
    }

    override fun applyBottomNavigationViewPadding(view: View, bottomNavigationViewHeight: Int) {
        binding.recyclerView.updatePadding(bottom = competitionItemsBottomPadding + bottomNavigationViewHeight)
    }

    private fun setupList() = with(binding) {
        itemsAdapter.apply {
            addLoadStateListener { loadState -> viewModel.bindPagingState(loadState) }
            onItemClick = { item ->

            }
        }
        recyclerView.apply {
            adapter = itemsAdapter.withLoadStateFooter(
                footer = PagingLoadStateAdapter { itemsAdapter.retry() }
            )
            addDefaultGridSpaceItemDecoration(spanCount = 2, spacing = R.dimen.padding_16)
            disableChangeAnimations()
        }
    }
}
