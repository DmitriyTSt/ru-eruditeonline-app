package ru.eruditeonline.app.presentation.ui.information

import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentInformationBinding
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.fitTopInsetsWithPadding
import ru.eruditeonline.app.presentation.navigation.observeNavigationCommands
import ru.eruditeonline.app.presentation.ui.base.BaseFragment
import javax.inject.Inject

class InformationFragment : BaseFragment(R.layout.fragment_information) {
    private val binding by viewBinding(FragmentInformationBinding::bind)
    private val viewModel: InformationViewModel by appViewModels()

    @Inject lateinit var webPagesAdapter: WebPagesAdapter

    override fun callOperations() {
        viewModel.loadWebPages()
    }

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        toolbar.fitTopInsetsWithPadding()
        toolbar.setNavigationOnClickListener {
            viewModel.navigateBack()
        }
        setupRecyclerView()
        stateViewFlipper.setRetryMethod { viewModel.loadWebPages() }
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
        webPagesLiveData.observe { state ->
            binding.stateViewFlipper.setState(state)
            state.doOnSuccess { pages ->
                webPagesAdapter.submitList(pages)
            }
        }
    }

    private fun setupRecyclerView() = with(binding.recyclerView) {
        webPagesAdapter.onItemClick = viewModel::openWebPage
        adapter = webPagesAdapter
    }
}