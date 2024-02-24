package ru.eruditeonline.app.domain.usecase.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.eruditeonline.architecture.presentation.model.LoadableState
import ru.eruditeonline.usecase.UseCaseUnary

@Deprecated("Usecase не должен управлять состоянием. Используйте execute.", replaceWith = ReplaceWith("execute(params)"))
fun <Params, Result> UseCaseUnary<Params, Result>.executeFlow(params: Params): Flow<LoadableState<Result>> = flow {
    try {
        emit(LoadableState.Loading())
        val result = execute(params)
        emit(LoadableState.Success(result))
    } catch (t: Throwable) {
        emit(LoadableState.Error(t))
    }
}
