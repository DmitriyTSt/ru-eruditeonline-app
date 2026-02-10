package ru.eruditeonline.app.presentation.composeui.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

val LocalViewModelFactory = staticCompositionLocalOf<ViewModelProvider.Factory> { error("ViewModelProvider.Factory not found") }

@Composable
inline fun <reified VM : ViewModel> appViewModel(): VM {
    val viewModelFactory = LocalViewModelFactory.current
    return viewModel {
        viewModelFactory.create(VM::class.java)
    }
}