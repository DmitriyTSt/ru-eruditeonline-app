package ru.eruditeonline.app.presentation.ui.result.user

import android.os.Bundle
import androidx.core.graphics.Insets
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentUserResultListBinding
import ru.eruditeonline.app.presentation.extension.addLinearSpaceItemDecoration
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.doOnApplyWindowInsets
import ru.eruditeonline.app.presentation.extension.hideSoftKeyboard
import ru.eruditeonline.app.presentation.extension.showSoftKeyboard
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
        viewModel.init(args.params)
    }

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        setupInsets()
        setupToolbar()
        setupSearch()
        stateViewFlipper.setRetryMethod { viewModel.init(args.params) }
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

    private fun setupInsets() = with(binding) {
        root.doOnApplyWindowInsets { _, insets, _ ->
            val windowInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime())
            toolbar.updatePadding(top = windowInsets.top)
            root.updatePadding(bottom = windowInsets.bottom)
            WindowInsetsCompat.Builder().setInsets(
                WindowInsetsCompat.Type.systemBars(),
                Insets.of(
                    windowInsets.left,
                    0,
                    windowInsets.right,
                    0
                )
            ).build()
        }
    }

    private fun setupToolbar() = with(binding) {
        val params = args.params
        toolbar.apply {
            setNavigationOnClickListener {
                viewModel.navigateBack()
            }
            title = when (params) {
                UserResultParams.All -> getString(R.string.user_result_list_title)
                else -> ""
            }
        }
        linearLayoutSearch.isVisible = params is UserResultParams.Email
        when (params) {
            UserResultParams.All -> {
                editTextSearch.setText("")
                editTextSearch.isFocusable = true
                editTextSearch.doAfterTextChanged {
                    viewModel.search(it?.toString().orEmpty())
                }
            }
            is UserResultParams.Email -> {
                editTextSearch.setText(params.email)
                editTextSearch.isFocusable = false
                editTextSearch.setOnClickListener {
                    viewModel.navigateBack()
                }
            }
        }
    }

    private fun setupSearch() = with(binding) {
        val searchCloseButton = toolbar.menu.findItem(R.id.search_close)
        val searchButton = toolbar.menu.findItem(R.id.search)
        if (args.params is UserResultParams.All) {
            searchCloseButton.isVisible = false
            searchCloseButton.setOnMenuItemClickListener {
                editTextSearch.setText("")
                activity?.hideSoftKeyboard()
                linearLayoutSearch.isVisible = false
                searchButton.isVisible = true
                searchCloseButton.isVisible = false

                true
            }
            searchButton.setOnMenuItemClickListener {
                linearLayoutSearch.isVisible = true
                editTextSearch.requestFocus()
                activity?.showSoftKeyboard(editTextSearch)
                searchButton.isVisible = false
                searchCloseButton.isVisible = true

                true
            }
        } else {
            searchButton.isVisible = false
            searchCloseButton.setOnMenuItemClickListener {
                viewModel.navigateBack()
                true
            }
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
        emptyView = binding.emptyView
        addLinearSpaceItemDecoration(R.dimen.padding_8)
    }
}
