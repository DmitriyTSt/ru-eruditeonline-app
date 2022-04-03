package ru.eruditeonline.app.data.model

sealed class LoadState<T> {
    class Loading<T> : LoadState<T>()
    class Success<T>(val data: T) : LoadState<T>()
    class Error<T>(val throwable: Throwable, val error: ParsedError? = null) : LoadState<T>()

    val isSuccess by lazy { this is Success }
    val isError by lazy { this is Error }
    val isLoading by lazy { this is Loading }
}
