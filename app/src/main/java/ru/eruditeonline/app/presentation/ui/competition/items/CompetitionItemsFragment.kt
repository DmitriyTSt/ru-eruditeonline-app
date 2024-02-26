package ru.eruditeonline.app.presentation.ui.competition.items

import android.os.Bundle
import android.view.View
import androidx.core.view.updatePadding
import androidx.fragment.app.setFragmentResultListener
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.competition.CompetitionFilters
import ru.eruditeonline.app.databinding.FragmentCompetitionItemsBinding
import ru.eruditeonline.app.presentation.extension.addDefaultGridSpaceItemDecoration
import ru.eruditeonline.app.presentation.extension.addLinearSpaceItemDecoration
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.fitTopInsetsWithPadding
import ru.eruditeonline.app.presentation.extension.resolveAttribute
import ru.eruditeonline.app.presentation.paging.PagingLoadStateAdapter
import ru.eruditeonline.app.presentation.ui.base.BaseFragment
import ru.eruditeonline.app.presentation.ui.competition.filter.CompetitionFilterFragment
import ru.eruditeonline.app.presentation.ui.competition.filter.model.FilterRequest
import ru.eruditeonline.app.presentation.ui.competition.items.adapter.CompetitionItemsAdapter
import ru.eruditeonline.architecture.presentation.model.LoadableState
import ru.eruditeonline.navigation.observeNavigationCommands
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
        setupHeader()
        setupList()
        setFragmentResultListener(CompetitionFilterFragment.FILTER_REQUEST_CODE) { _, bundle ->
            val filterRequest = bundle.getParcelable<FilterRequest>(CompetitionFilterFragment.FILTER_KEY)
            if (filterRequest != null) {
                stateViewFlipper.setState(LoadableState.Loading<Unit>())
                itemsAdapter.submitData(lifecycle, PagingData.empty())
                viewModel.loadCompetitions(
                    query = null,
                    ageIds = filterRequest.ageIds,
                    subjectIds = filterRequest.subjectIds,
                )
            }
        }
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
        pagingStateLiveData.observe { state ->
            binding.stateViewFlipper.setState(state)
        }
        pagingDataLiveData.observe { data ->
            itemsAdapter.submitData(lifecycle, data)
        }
        listViewTypeLiveData.observe { viewType ->
            applyProductListViewType(viewType)
        }
        filtersLiveData.observe { filters ->
            bindFilters(filters)
        }
    }

    override fun applyBottomNavigationViewPadding(view: View, bottomNavigationViewHeight: Int) {
        binding.recyclerView.updatePadding(bottom = competitionItemsBottomPadding + bottomNavigationViewHeight)
    }

    private fun setupHeader() = with(binding) {
        toolbar.menu.findItem(R.id.view_type).setOnMenuItemClickListener {
            viewModel.changeListViewType()
            true
        }
    }

    private fun setupList() = with(binding) {
        itemsAdapter.apply {
            addLoadStateListener { loadState -> viewModel.bindPagingState(loadState) }
            onItemClick = viewModel::openCompetition
        }
        recyclerView.apply {
            adapter = itemsAdapter.withLoadStateFooter(
                footer = PagingLoadStateAdapter { itemsAdapter.retry() }
            )
            itemAnimator = null
        }
        recyclerView.emptyView = emptyView
        applyProductListViewType(itemsAdapter.viewType)
    }

    private fun bindFilters(filters: CompetitionFilters) = with(binding) {
        toolbar.menu.findItem(R.id.filter).setOnMenuItemClickListener {
            viewModel.openFilter(filters)
            true
        }
    }

    private fun applyProductListViewType(viewType: CompetitionItemsViewType) = with(binding) {
        toolbar.menu.findItem(R.id.view_type).setIcon(
            when (viewType) {
                CompetitionItemsViewType.CARD -> root.context.resolveAttribute(R.attr.iconViewTypeCard)
                CompetitionItemsViewType.ROW -> root.context.resolveAttribute(R.attr.iconViewTypeRow)
            }
        )
        itemsAdapter.let {
            it.spanCount = if (viewType == CompetitionItemsViewType.CARD) 2 else 1
            it.viewType = viewType
        }
        recyclerView.apply {
            if (itemDecorationCount > 0) {
                removeItemDecorationAt(0)
            }
            when (viewType) {
                CompetitionItemsViewType.CARD -> {
                    layoutManager = GridLayoutManager(context, 2)
                    addDefaultGridSpaceItemDecoration(spanCount = 2, spacing = R.dimen.padding_16)
                    setupProgressItemSpanSize()
                }
                CompetitionItemsViewType.ROW -> {
                    layoutManager = LinearLayoutManager(context)
                    addLinearSpaceItemDecoration(R.dimen.padding_12)
                }
            }
        }
        itemsAdapter.notifyDataSetChanged()
    }

    private fun setupProgressItemSpanSize() = with(binding.recyclerView) {
        (layoutManager as? GridLayoutManager)?.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return itemsAdapter.getSpanSize(position)
            }
        }
    }
}
