package ru.eruditeonline.app.presentation.extension

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import ru.eruditeonline.app.presentation.ui.base.BaseActivity
import ru.eruditeonline.app.presentation.ui.base.BaseFragment
import ru.eruditeonline.injectviewmodel.presentation.appActivityViewModels
import ru.eruditeonline.injectviewmodel.presentation.appViewModels

@MainThread
inline fun <reified VM : ViewModel> BaseFragment.appViewModels() = appViewModels<VM> { viewModelFactory }

@MainThread
inline fun <reified VM : ViewModel> BaseFragment.appActivityViewModels() =
    appActivityViewModels<VM> { viewModelFactory }

@MainThread
inline fun <reified VM : ViewModel> BaseActivity.appActivityViewModels() =
    appActivityViewModels<VM> { viewModelFactory }
