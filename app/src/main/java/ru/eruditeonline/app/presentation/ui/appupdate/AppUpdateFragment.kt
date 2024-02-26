package ru.eruditeonline.app.presentation.ui.appupdate

import android.os.Bundle
import androidx.activity.addCallback
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.base.AppUpdate
import ru.eruditeonline.app.databinding.FragmentAppUpdateBinding
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.fitTopInsetsWithPadding
import ru.eruditeonline.app.presentation.extension.getDrawableCompat
import ru.eruditeonline.app.presentation.ui.base.BaseFragment
import ru.eruditeonline.navigation.observeNavigationCommands

/**
 * Экран обновления приложения
 */
class AppUpdateFragment : BaseFragment(R.layout.fragment_app_update) {

    private val binding by viewBinding(FragmentAppUpdateBinding::bind)
    private val viewModel: AppUpdateViewModel by appViewModels()
    private val args: AppUpdateFragmentArgs by navArgs()

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        toolbar.fitTopInsetsWithPadding()
        toolbar.setNavigationOnClickListener {
            closeScreen()
        }
        buttonUpdate.setOnClickListener {
            viewModel.openAppUpdate()
        }
        bindAppUpdate(args.appUpdate)
    }

    override fun onBindViewModel() {
        observeNavigationCommands(viewModel)
    }

    private fun bindAppUpdate(appUpdate: AppUpdate) = with(binding) {
        if (!appUpdate.forceUpdate) {
            activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner) {
                closeScreen()
            }
        }
        toolbar.navigationIcon = if (appUpdate.forceUpdate) {
            null
        } else {
            root.context.getDrawableCompat(R.drawable.ic_close)
        }
    }

    private fun closeScreen() {
        viewModel.openMainScreen()
    }
}
