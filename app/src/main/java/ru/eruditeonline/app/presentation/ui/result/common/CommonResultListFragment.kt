package ru.eruditeonline.app.presentation.ui.result.common

import android.os.Bundle
import android.view.View
import androidx.core.view.updatePadding
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentCommonResultListBinding
import ru.eruditeonline.app.presentation.paging.PagingLoadStateAdapter
import ru.eruditeonline.navigation.observeNavigationCommands
import ru.eruditeonline.ui.presentation.base.BaseFragment
import ru.eruditeonline.ui.presentation.ext.addLinearSpaceItemDecoration
import ru.eruditeonline.ui.presentation.ext.appViewModels
import ru.eruditeonline.ui.presentation.ext.fitTopInsetsWithPadding
import javax.inject.Inject

class CommonResultListFragment : BaseFragment(R.layout.fragment_common_result_list) {

    override val showBottomNavigationView: Boolean = true

    private val binding by viewBinding(FragmentCommonResultListBinding::bind)
    private val viewModel: CommonResultListViewModel by appViewModels()

    private val padding16 by lazy { resources.getDimensionPixelSize(R.dimen.padding_16) }

    @Inject lateinit var commonResultsAdapter: CommonResultsAdapter

    override fun callOperations() {
        viewModel.load()
    }

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        toolbar.fitTopInsetsWithPadding()
        toolbar.setNavigationOnClickListener {
            viewModel.navigateBack()
        }
        stateViewFlipper.setRetryMethod { viewModel.load() }
        setupList()
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
        resultsLiveData.observe { data ->
            commonResultsAdapter.submitData(lifecycle, data)
        }
        pagingStateLiveData.observe { state ->
            binding.stateViewFlipper.setState(state)
        }
    }

    override fun applyBottomNavigationViewPadding(view: View, bottomNavigationViewHeight: Int) {
        binding.recyclerView.updatePadding(bottom = bottomNavigationViewHeight + padding16)
    }

    private fun setupList() = with(binding.recyclerView) {
        commonResultsAdapter.apply {
            addLoadStateListener { loadState -> viewModel.bindPagingState(loadState) }
            onItemClick = viewModel::openCompetition
        }
        adapter = commonResultsAdapter.withLoadStateFooter(
            footer = PagingLoadStateAdapter { commonResultsAdapter.retry() }
        )
        addLinearSpaceItemDecoration(R.dimen.padding_8)
    }
}
