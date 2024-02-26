package ru.eruditeonline.app.presentation.ui.auth.registrationsuccess

import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentRegistrationSuccessBinding
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.fitTopInsetsWithPadding
import ru.eruditeonline.app.presentation.ui.base.BaseFragment
import ru.eruditeonline.navigation.observeNavigationCommands

class RegistrationSuccessFragment : BaseFragment(R.layout.fragment_registration_success) {
    private val binding by viewBinding(FragmentRegistrationSuccessBinding::bind)
    private val viewModel: RegistrationSuccessViewModel by appViewModels()

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        toolbar.fitTopInsetsWithPadding()
        toolbar.setNavigationOnClickListener {
            viewModel.navigateBack()
        }
        buttonOk.setOnClickListener {
            viewModel.navigateBack()
        }
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
    }
}
