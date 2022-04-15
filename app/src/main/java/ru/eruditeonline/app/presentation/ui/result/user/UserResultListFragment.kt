package ru.eruditeonline.app.presentation.ui.result.user

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentUserResultListBinding
import ru.eruditeonline.app.presentation.extension.addLinearSpaceItemDecoration
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.fitTopInsetsWithPadding
import ru.eruditeonline.app.presentation.navigation.observeNavigationCommands
import ru.eruditeonline.app.presentation.paging.PagingLoadStateAdapter
import ru.eruditeonline.app.presentation.ui.base.BaseFragment
import javax.inject.Inject

class UserResultListFragment : BaseFragment(R.layout.fragment_user_result_list) {
    private val binding by viewBinding(FragmentUserResultListBinding::bind)
    private val viewModel: UserResultListViewModel by appViewModels()
    private val args: UserResultListFragmentArgs by navArgs()

    @Inject lateinit var userResultsAdapter: UserResultsAdapter

    override fun callOperations() {
        viewModel.load(args.params)
    }

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        setupToolbar()
        stateViewFlipper.setRetryMethod { viewModel.load(args.params) }
        setupList()
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
        pagingStateLiveData.observe { state ->
            binding.stateViewFlipper.setState(state)
        }
        resultsLiveData.observe { data ->
            userResultsAdapter.submitData(lifecycle, data)
        }
    }

    private fun setupToolbar() = with(binding) {
        val params = args.params
        toolbar.apply {
            fitTopInsetsWithPadding()
            setNavigationOnClickListener {
                viewModel.navigateBack()
            }
            title = when (params) {
                UserResultParams.All -> getString(R.string.user_result_list_title)
                else -> ""
            }
        }
        linearLayoutSearch.isVisible = params !is UserResultParams.All
        editTextSearch.setText(
            when (params) {
                UserResultParams.All -> ""
                is UserResultParams.Email -> params.email
                is UserResultParams.Query -> params.query
            }
        )
        editTextSearch.setOnClickListener {
            viewModel.navigateBack()
        }
        imageViewClose.setOnClickListener {
            viewModel.navigateBack()
        }
    }

    private fun setupList() = with(binding.recyclerView) {
        userResultsAdapter.apply {
            addLoadStateListener { loadState -> viewModel.bindPagingState(loadState) }
            onItemClick = viewModel::openResult
        }
        adapter = userResultsAdapter.withLoadStateFooter(
            footer = PagingLoadStateAdapter { userResultsAdapter.retry() }
        )
        addLinearSpaceItemDecoration(R.dimen.padding_8)
    }
}
