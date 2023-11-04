package ru.eruditeonline.app.presentation.composeui.paging

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

fun LazyGridScope.applyFooterState(spanCount: Int, items: LazyPagingItems<*>) {
    when (items.loadState.append) {
        is LoadState.Loading -> {
            item(span = { GridItemSpan(spanCount) }) { PageLoader(modifier = Modifier.fillMaxSize()) }
        }

        is LoadState.Error -> {
            val error = items.loadState.append as LoadState.Error
            item(span = { GridItemSpan(spanCount) }) {
                PageError(throwable = error.error, modifier = Modifier.fillMaxSize(), onRetryClick = { items.retry() })
            }
        }
        is LoadState.NotLoading -> Unit
    }
}