package ru.eruditeonline.app.presentation.ui.competition.items

import android.os.Bundle
import android.view.View
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentCompetitionItemsBinding
import ru.eruditeonline.app.presentation.extension.addDefaultGridSpaceItemDecoration
import ru.eruditeonline.app.presentation.extension.addLinearSpaceItemDecoration
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
        setupHeader()
        setupList()
    }

    override fun onBindViewModel() = with(viewModel) {
        pagingStateLiveData.observe { state ->
            binding.stateViewFlipper.setState(state)
        }
        pagingDataLiveData.observe { data ->
            itemsAdapter.submitData(lifecycle, data)
        }
        listViewTypeLiveData.observe { viewType ->
            applyProductListViewType(viewType)
        }
    }

    override fun applyBottomNavigationViewPadding(view: View, bottomNavigationViewHeight: Int) {
        binding.recyclerView.updatePadding(bottom = competitionItemsBottomPadding + bottomNavigationViewHeight)
    }

    private fun setupHeader() = with(binding) {
        imageViewViewType.setOnClickListener {
            viewModel.changeListViewType()
        }
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
            disableChangeAnimations()
        }
        applyProductListViewType(itemsAdapter.viewType)
    }

    private fun applyProductListViewType(viewType: CompetitionItemsViewType) = with(binding) {
        imageViewViewType.setImageResource(
            when (viewType) {
                CompetitionItemsViewType.CARD -> R.drawable.ic_view_type_card
                CompetitionItemsViewType.ROW -> R.drawable.ic_view_type_row
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
