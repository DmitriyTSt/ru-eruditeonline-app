package ru.eruditeonline.app.presentation.extension

import androidx.annotation.MainThread
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import ru.eruditeonline.app.presentation.ui.base.BaseActivity
import ru.eruditeonline.app.presentation.ui.base.BaseFragment

@MainThread
inline fun <reified VM : ViewModel> BaseFragment.appViewModels() =
    createViewModelLazy(VM::class, { this.viewModelStore }, factoryProducer = { viewModelFactory })

@MainThread
inline fun <reified VM : ViewModel> BaseFragment.appActivityViewModels() =
    createViewModelLazy(
        VM::class,
        { requireActivity().viewModelStore },
        factoryProducer = {
            if (requireActivity() is BaseActivity) {
                (requireActivity() as BaseActivity).viewModelFactory
            } else {
                requireActivity().defaultViewModelProviderFactory
            }
        }
    )
