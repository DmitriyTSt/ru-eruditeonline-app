package ru.eruditeonline.app.presentation.ui.auth.registration

import android.os.Bundle
import android.text.method.LinkMovementMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentRegistrationBinding
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.fitTopInsetsWithPadding
import ru.eruditeonline.app.presentation.extension.setTextFromHtml
import ru.eruditeonline.app.presentation.extension.stripUnderlines
import ru.eruditeonline.app.presentation.navigation.observeNavigationCommands
import ru.eruditeonline.app.presentation.ui.base.BaseFragment

class RegistrationFragment : BaseFragment(R.layout.fragment_registration) {
    private val binding by viewBinding(FragmentRegistrationBinding::bind)
    private val viewModel: RegistrationViewModel by appViewModels()

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        toolbar.fitTopInsetsWithPadding()
        toolbar.setNavigationOnClickListener {
            viewModel.navigateBack()
        }
        setupPrivacyPolicy()
        setupSelectCountry()
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
    }

    private fun setupPrivacyPolicy() = with(binding.textViewPrivacyPolicy) {
        movementMethod = LinkMovementMethod.getInstance()
        setTextFromHtml(getString(R.string.registration_privacy_policy_checkbox_text))
        stripUnderlines()
    }

    private fun setupSelectCountry() = with(binding) {
        viewSelectCountry.setOnClickListener {
            viewModel.openSelectCountry()
        }
    }
}
