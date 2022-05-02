package ru.eruditeonline.app.presentation.ui.debug

import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentDebugBinding
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.fitTopInsetsWithPadding
import ru.eruditeonline.app.presentation.navigation.observeNavigationCommands
import ru.eruditeonline.app.presentation.ui.base.BaseFragment
import kotlin.system.exitProcess

class DebugFragment : BaseFragment(R.layout.fragment_debug) {
    private val binding by viewBinding(FragmentDebugBinding::bind)
    private val viewModel: DebugViewModel by appViewModels()

    override fun callOperations() {
        viewModel.initEndpoint()
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
        showDevEndpointLiveEvent.observe { isShow ->
            if (isShow) {
                binding.radioGroupDebugServer.clearCheck()
                binding.radioButtonDebugDebug.isChecked = true
            }
        }
        showReleaseEndpointLiveEvent.observe { isShow ->
            if (isShow) {
                binding.radioGroupDebugServer.clearCheck()
                binding.radioButtonDebugRelease.isChecked = true
            }
        }
        showCustomEndpointLiveEvent.observe { endpoint ->
            if (endpoint.isNotEmpty()) {
                binding.radioGroupDebugServer.clearCheck()
                binding.radioButtonDebugCustom.isChecked = true
                binding.editTextCustomEndpoint.setText(endpoint)
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
        val intent = context
            ?.packageManager
            ?.getLaunchIntentForPackage(requireContext().packageName)
        intent?.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        exitProcess(0)
    }
}