package ru.eruditeonline.app.domain.usecase.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.eruditeonline.app.data.model.LoadState
import ru.eruditeonline.app.data.model.ParsedError

abstract class UseCaseUnary<in Params, Result> {

    abstract suspend fun execute(params: Params): Result

    fun executeFlow(params: Params): Flow<LoadState<Result>> = flow {
        try {
            emit(LoadState.Loading<Result>())
            val result = execute(params)
            emit(LoadState.Success(result))
        } catch (t: Throwable) {
            emit(LoadState.Error<Result>(ParsedError("", ParsedError.DEFAULT_TITLE, ParsedError.DEFAULT_MESSAGE), t))
        }
    }
}
