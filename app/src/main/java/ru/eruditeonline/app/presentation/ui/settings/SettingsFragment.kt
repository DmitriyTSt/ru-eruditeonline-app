package ru.eruditeonline.app.presentation.ui.settings

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentSettingsBinding
import ru.eruditeonline.app.presentation.managers.Theme
import ru.eruditeonline.navigation.observeNavigationCommands
import ru.eruditeonline.ui.presentation.base.BaseFragment
import ru.eruditeonline.ui.presentation.ext.appViewModels
import ru.eruditeonline.ui.presentation.ext.fitTopInsetsWithPadding

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {
    private val binding by viewBinding(FragmentSettingsBinding::bind)
    private val viewModel: SettingsViewModel by appViewModels()

    override fun callOperations() {
        viewModel.loadData()
    }

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        toolbar.fitTopInsetsWithPadding()
        toolbar.setNavigationOnClickListener {
            viewModel.navigateBack()
        }
        setupThemePicker()
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
        dataLiveData.observe { state ->
            binding.stateViewFlipper.setState(state)
            state.doOnSuccess { data ->
                bindData(data)
            }
        }
        themeChangedLiveEvent.observe {
            AlertDialog.Builder(binding.root.context)
                .setTitle(R.string.change_theme_dialog_title)
                .setMessage(R.string.change_theme_dialog_message)
                .setPositiveButton(R.string.yes) { _, _ ->
                    viewModel.restartApp()
                }
                .setNegativeButton(R.string.no, null)
                .show()
        }
    }

    private fun setupThemePicker() = with(binding.content) {
        cardViewColoredTheme.setOnClickListener {
            viewModel.changeTheme(Theme.COLORED)
        }
        cardViewLightTheme.setOnClickListener {
            viewModel.changeTheme(Theme.LIGHT)
        }
    }

    private fun bindData(data: SettingsData) {
        bindTheme(data.theme)
    }

    private fun bindTheme(theme: Theme) = with(binding.content) {
        imageViewColoredSelector.isVisible = theme == Theme.COLORED
        imageViewLightSelector.isVisible = theme == Theme.LIGHT
    }
}
