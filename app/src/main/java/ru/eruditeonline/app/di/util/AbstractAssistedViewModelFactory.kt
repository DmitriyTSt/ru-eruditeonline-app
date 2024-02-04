package ru.eruditeonline.app.di.util

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

interface AbstractAssistedViewModelFactory<T : ViewModel> {
    fun create(savedStateHandle: SavedStateHandle): T
}