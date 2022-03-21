package ru.eruditeonline.app.domain.usecase.base

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

abstract class UseCasePaged<in Params, Result : Any> {

    abstract fun execute(params: Params): Flow<PagingData<Result>>

    fun executeFlow(params: Params): Flow<PagingData<Result>> {
        return execute(params)
    }
}
