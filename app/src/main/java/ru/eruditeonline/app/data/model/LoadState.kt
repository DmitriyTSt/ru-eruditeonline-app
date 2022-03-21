package ru.eruditeonline.app.data.model

sealed class LoadState<T> {
    class Loading<T> : LoadState<T>()
    class Success<T>(val data: T) : LoadState<T>()
    class Error<T>(val message: String, val throwable: Throwable) : LoadState<T>()
}
