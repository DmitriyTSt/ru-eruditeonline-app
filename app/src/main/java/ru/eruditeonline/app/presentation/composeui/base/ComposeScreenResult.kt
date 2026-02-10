package ru.eruditeonline.app.presentation.composeui.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf

// AI Generated Надо будет подумать переписать

class ScreenResultDispatcher {
    val screenResults = mutableStateMapOf<String, Any>()

    fun <T : Any> setResult(result: T) {
        screenResults[result::class.java.name] = result
    }

    inline fun <reified T : Any> consumeResult(): T? {
        val key = T::class.java.name
        val result = screenResults[key] as? T
        if (result != null) {
            screenResults.remove(key)
        }
        return result
    }
}

val LocalScreenResultDispatcher = staticCompositionLocalOf<ScreenResultDispatcher> {
    error("ScreenResultDispatcher not found")
}

@Composable
fun rememberScreenResultDispatcher() = remember { ScreenResultDispatcher() }

@Composable
inline fun <reified T : Any> ObserveScreenResult(crossinline onResult: (T) -> Unit) {
    val screenResultDispatcher = LocalScreenResultDispatcher.current
    val result = screenResultDispatcher.consumeResult<T>()
    LaunchedEffect(result) {
        if (result != null) {
            onResult(result)
        }
    }
}