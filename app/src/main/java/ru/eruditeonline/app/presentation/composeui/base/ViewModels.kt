package ru.eruditeonline.app.presentation.composeui.base

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
inline fun <reified VM : ViewModel> appViewModel(viewModelFactory: ViewModelProvider.Factory): VM {
    return viewModel {
        viewModelFactory.create(VM::class.java)
    }
}