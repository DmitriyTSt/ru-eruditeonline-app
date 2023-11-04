package ru.eruditeonline.app.presentation.paging.base

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow

/**
 * Базовая фабрика создания PagingFlow
 *
 * Именно она инжектится во вью модель для запуска пагинации
 *
 * Пример:
 * ```
 *  _loadSomePagingDataLiveData.launchPagingData { examplePagingFlow.create(Unit) }
 * ```
 */
abstract class BasePagingFlowFactory<in Params, Key : Any, Result : Any> {

    /**
     * Создание PagingFlow
     */
    abstract fun create(params: Params): Flow<PagingData<Result>>

    /**
     * Создание пейджера пагиниации
     *
     * @param pagingSource реализация PagingSource
     * @param limit размер страницы пагинации
     */
    protected fun createPager(
        pagingSource: PagingSource<Key, Result>,
        limit: Int = BasePagingSource.DEFAULT_LIMIT,
    ): Pager<Key, Result> {
        return Pager(
            config = PagingConfig(
                pageSize = limit,
                prefetchDistance = limit / 2,
                enablePlaceholders = false,
                initialLoadSize = limit,
            ),
            pagingSourceFactory = { pagingSource },
        )
    }
}
