package ru.eruditeonline.app.presentation.ui.auth.validationsuccess

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentValidationSuccessBinding
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.fitTopInsetsWithPadding
import ru.eruditeonline.app.presentation.ui.base.BaseFragment
import ru.eruditeonline.navigation.observeNavigationCommands

class ValidationSuccessFragment : BaseFragment(R.layout.fragment_validation_success) {
    private val binding by viewBinding(FragmentValidationSuccessBinding::bind)
    private val viewModel: ValidationSuccessViewModel by appViewModels()
    private val args: ValidationSuccessFragmentArgs by navArgs()

    override fun callOperations() {
        viewModel.confirmEmail(args.token)
    }

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        toolbar.fitTopInsetsWithPadding()
        toolbar.setNavigationOnClickListener {
            viewModel.navigateBack()
        }
        buttonAuth.setOnClickListener {
            viewModel.openAuth()
        }
        stateViewFlipper.setRetryMethod { viewModel.confirmEmail(args.token) }
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
        confirmEmailLiveEvent.observe { state ->
            binding.stateViewFlipper.setState(state)
        }
    }
}
