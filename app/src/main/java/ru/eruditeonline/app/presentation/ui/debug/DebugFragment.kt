package ru.eruditeonline.app.presentation.ui.debug

import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentDebugBinding
import ru.eruditeonline.app.domain.usecase.debug.GetDebugDataUseCase
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.fitTopInsetsWithPadding
import ru.eruditeonline.app.presentation.navigation.AppStarter
import ru.eruditeonline.app.presentation.navigation.observeNavigationCommands
import ru.eruditeonline.app.presentation.ui.base.BaseFragment
import javax.inject.Inject
import kotlin.system.exitProcess

class DebugFragment : BaseFragment(R.layout.fragment_debug) {
    private val binding by viewBinding(FragmentDebugBinding::bind)
    private val viewModel: DebugViewModel by appViewModels()

    @Inject lateinit var appStarter: AppStarter

    override fun callOperations() {
        viewModel.callOperations {
            viewModel.initData()
        }
    }

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        toolbar.fitTopInsetsWithPadding()
        toolbar.setNavigationOnClickListener {
            viewModel.navigateBack()
        }
        setupSaveButton()
        radioButtonDebugCustom.setOnCheckedChangeListener { _, isChecked ->
            textInputLayoutCustomEndpoint.isVisible = isChecked
        }
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
        debugDataLiveData.observe { result ->
            result.doOnSuccess { data ->
                bindData(data)
            }
        }
        restartAppLiveEvent.observe { result ->
            binding.buttonDebugSave.setState(result)
            result.doOnSuccess { isChanged ->
                if (isChanged) {
                    restartApp()
                }
            }
        }
    }

    private fun bindData(data: GetDebugDataUseCase.Result) = with(binding) {
        radioButtonDebugRelease.text = getString(R.string.debug_release, data.prodEndpoint)
        radioButtonDebugDebug.text = getString(R.string.debug_debug, data.devEndpoint)
        radioButtonDebugDebug.isChecked = data.devEndpointEnabled
        radioButtonDebugRelease.isChecked = data.prodEndpointEnabled
        radioButtonDebugCustom.isChecked = data.customEndpointEnabled
        if (data.customEndpointEnabled) {
            editTextCustomEndpoint.setText(data.customEndpoint)
        }
        switchComposeEnabled.isChecked = data.isComposeEnabled
        switchComposeEnabled.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setComposeEnabled(isChecked)
        }
    }

    private fun setupSaveButton() = with(binding) {
        buttonDebugSave.setOnClickListener {
            if (radioButtonDebugCustom.isChecked && textInputLayoutCustomEndpoint.validate() ||
                !radioButtonDebugCustom.isChecked
            ) {
                viewModel.applyNewEndpoint(
                    radioButtonDebugDebug.isChecked,
                    radioButtonDebugRelease.isChecked,
                    radioButtonDebugCustom.isChecked,
                    editTextCustomEndpoint.text.toString(),
                )
            }
        }
    }

    private fun restartApp() {
        val intent = appStarter.createStartIntent(requireContext())
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        exitProcess(0)
    }
}
