package ru.eruditeonline.app.presentation.ui.information

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.updatePadding
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentInformationBinding
import ru.eruditeonline.core.domain.repository.AppInfoRepository
import ru.eruditeonline.navigation.observeNavigationCommands
import ru.eruditeonline.ui.presentation.base.BaseFragment
import ru.eruditeonline.ui.presentation.ext.appViewModels
import ru.eruditeonline.ui.presentation.ext.fitTopInsetsWithPadding
import javax.inject.Inject

class InformationFragment : BaseFragment(R.layout.fragment_information) {

    override val showBottomNavigationView: Boolean = true

    private val binding by viewBinding(FragmentInformationBinding::bind)
    private val viewModel: InformationViewModel by appViewModels()
    private val padding16 by lazy { resources.getDimensionPixelSize(R.dimen.padding_16) }

    @Inject lateinit var webPagesAdapter: WebPagesAdapter
    @Inject lateinit var appInfoRepository: AppInfoRepository

    override fun callOperations() {
        viewModel.loadWebPages()
    }

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        toolbar.fitTopInsetsWithPadding()
        toolbar.setNavigationOnClickListener {
            viewModel.navigateBack()
        }
        setupRecyclerView()
        setupAppVersion()
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

    override fun applyBottomNavigationViewPadding(view: View, bottomNavigationViewHeight: Int) {
        binding.linearLayoutContent.updatePadding(bottom = bottomNavigationViewHeight + padding16)
    }

    private fun setupRecyclerView() = with(binding.recyclerView) {
        webPagesAdapter.onItemClick = viewModel::openWebPage
        adapter = webPagesAdapter
    }

    @SuppressLint("SetTextI18n")
    private fun setupAppVersion() {
        binding.textViewAppVersion.text = "${appInfoRepository.versionNameWithSuffix}(${appInfoRepository.versionCode})"
    }
}
