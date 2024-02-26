package ru.eruditeonline.app.presentation.ui.splash

import android.os.Bundle
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.mapper.parseError
import ru.eruditeonline.app.databinding.FragmentSplashBinding
import ru.eruditeonline.app.presentation.extension.appActivityViewModels
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.ui.base.BaseFragment
import ru.eruditeonline.architecture.domain.ParsedError
import ru.eruditeonline.architecture.presentation.model.LoadableState
import ru.eruditeonline.navigation.observeNavigationCommands
import timber.log.Timber

private const val STATE_DATA = 0
private const val STATE_ERROR = 1

class SplashFragment : BaseFragment(R.layout.fragment_splash) {
    private val binding by viewBinding(FragmentSplashBinding::bind)
    private val viewModel: SplashViewModel by appViewModels()
    private val splashStartFlowViewModel: SplashStartFlowViewModel by appActivityViewModels()

    override fun callOperations() {
        viewModel.initDebugButton()
        splashStartFlowViewModel.runStartFlow()
    }

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        buttonRepeat.setOnClickListener {
            splashStartFlowViewModel.runStartFlow()
        }
        setupDebugButton()
    }

    override fun onBindViewModel() {
        observeNavigationCommands(viewModel)
        observeNavigationCommands(splashStartFlowViewModel)
        splashStartFlowViewModel.initialFlowLiveEvent.observe { state ->
            if (state.isError) {
                binding.root.displayedChild = STATE_ERROR
            } else {
                binding.root.displayedChild = STATE_DATA
            }
            state.doOnError { error ->
                Timber.e((state as LoadableState.Error).throwable)
                bindErrorText(error.parseError())
            }
        }
        viewModel.isDebugButtonVisibleLiveData.observe { state ->
            state.doOnSuccess { isVisible ->
                binding.buttonDebug.isVisible = isVisible
            }
        }
    }

    private fun setupDebugButton() = with(binding) {
        buttonDebug.setOnClickListener {
            viewModel.openDebug()
        }
    }

    private fun bindErrorText(error: ParsedError) = with(binding) {
        textViewErrorTitle.text = when (error) {
            is ParsedError.NetworkError -> getString(R.string.error_no_network_title)
            else -> getString(R.string.error_something_wrong_title)
        }
        textViewErrorMessage.text = when (error) {
            is ParsedError.ApiError -> error.message
            is ParsedError.GeneralError -> getString(R.string.error_something_wrong_description)
            is ParsedError.NetworkError -> getString(R.string.error_no_network_description)
        }
    }
}
