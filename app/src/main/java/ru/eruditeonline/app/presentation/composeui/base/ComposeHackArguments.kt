package ru.eruditeonline.app.presentation.composeui.base

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController

//
// TODO Этот файл - костыли, пока не придумаю нормальную типизированную удобную навигацию по роутам
//

inline fun <reified T : Parcelable> NavController.setNextScreenArguments(arg: T) {
    currentBackStackEntry?.savedStateHandle?.set(T::class.java.name, arg)
}

@Composable
inline fun <reified T> NavController.GetScreenArguments(block: (T) -> Unit) {
    val screenResultState = previousBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<T>(T::class.java.name)?.observeAsState()

    screenResultState?.value?.let {
        block(it)

        previousBackStackEntry
            ?.savedStateHandle
            ?.remove<T>(T::class.java.name)
    }
}