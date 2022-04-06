package ru.eruditeonline.app.data.model

sealed class LoadableState<T> {
    class Loading<T> : LoadableState<T>()
    class Success<T>(val data: T) : LoadableState<T>()
    class Error<T>(val throwable: Throwable, val error: ParsedError? = null) : LoadableState<T>()

    val isSuccess by lazy { this is Success }
    val isError by lazy { this is Error }
    val isLoading by lazy { this is Loading }

    fun doOnSuccess(block: (T) -> Unit) {
        if (isSuccess) {
            block((this as Success).data)
        }
    }

    fun doOnError(block: (ParsedError?) -> Unit) {
        if (isError) {
            block((this as Error).error)
        }
    }

    fun doOnLoading(block: () -> Unit) {
        if (isLoading) {
            block()
        }
    }
}
