package ru.eruditeonline.app.data.model

sealed class LoadState<T> {
    class Loading<T> : LoadState<T>()
    class Success<T>(val data: T) : LoadState<T>()
    class Error<T>(val error: ParsedError, val throwable: Throwable) : LoadState<T>()
}
