package ru.eruditeonline.ui.presentation.ext

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import ru.eruditeonline.ui.presentation.base.BaseActivity
import ru.eruditeonline.ui.presentation.base.BaseFragment
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
