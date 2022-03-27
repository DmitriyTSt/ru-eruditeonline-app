package ru.eruditeonline.app.domain.paging.base

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState

const val DEFAULT_OFFSET = 0
const val DEFAULT_LIMIT = 20

abstract class BasePagingSource<T : Any> : PagingSource<Int, T>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val offset = params.key ?: DEFAULT_OFFSET
        val limit = params.loadSize

        return try {
            val result = loadPage(offset, limit)

            val nextKey = if (result.size < limit || result.isEmpty()) {
                null
            } else {
                offset + result.size
            }

            LoadResult.Page(
                data = result,
                prevKey = if (offset == DEFAULT_OFFSET) null else offset - 1,
                nextKey = nextKey
            )
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }

    abstract suspend fun loadPage(offset: Int, limit: Int): List<T>

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition
    }
}

fun <T : Any> createPager(pagingSource: PagingSource<Int, T>, limit: Int = DEFAULT_LIMIT): Pager<Int, T> {
    return Pager(
        config = PagingConfig(
            pageSize = limit,
            prefetchDistance = limit / 2,
            enablePlaceholders = false,
            initialLoadSize = limit
        ),
        pagingSourceFactory = { pagingSource }
    )
}
