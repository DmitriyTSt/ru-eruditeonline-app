package ru.eruditeonline.app.presentation.ui.splash

import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentSplashBinding
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.navigation.observeNavigationCommands
import ru.eruditeonline.app.presentation.ui.base.BaseFragment

private const val STATE_DATA = 0
private const val STATE_ERROR = 1

class SplashFragment : BaseFragment(R.layout.fragment_splash) {
    private val binding by viewBinding(FragmentSplashBinding::bind)
    private val viewModel: SplashViewModel by appViewModels()

    override fun callOperations() {
        viewModel.runStartFlow()
    }

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        buttonRepeat.setOnClickListener {
            viewModel.runStartFlow()
        }
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
        startFlowLiveEvent.observe { state ->
            if (state.isError) {
                binding.root.displayedChild = STATE_ERROR
            } else {
                binding.root.displayedChild = STATE_DATA
            }
        }
    }
}
