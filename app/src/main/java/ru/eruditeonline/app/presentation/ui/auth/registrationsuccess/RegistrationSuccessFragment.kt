package ru.eruditeonline.app.presentation.ui.auth.registrationsuccess

import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentRegistrationSuccessBinding
import ru.eruditeonline.navigation.observeNavigationCommands
import ru.eruditeonline.ui.presentation.base.BaseFragment
import ru.eruditeonline.ui.presentation.ext.appViewModels
import ru.eruditeonline.ui.presentation.ext.fitTopInsetsWithPadding

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
