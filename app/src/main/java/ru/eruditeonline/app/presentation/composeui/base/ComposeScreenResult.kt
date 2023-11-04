package ru.eruditeonline.app.presentation.composeui.base

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController

inline fun <reified T : Parcelable> NavController.setResult(result: T) {
    previousBackStackEntry?.savedStateHandle?.set(T::class.java.name, result)
}

@Composable
inline fun <reified T> NavController.SetResultListener(onResult: (T) -> Unit) {
    val screenResultState = currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<T>(T::class.java.name)?.observeAsState()

    screenResultState?.value?.let {
        onResult(it)

        currentBackStackEntry
            ?.savedStateHandle
            ?.remove<T>(T::class.java.name)
    }
}