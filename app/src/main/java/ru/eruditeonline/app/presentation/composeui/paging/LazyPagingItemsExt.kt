package ru.eruditeonline.app.presentation.composeui.paging

import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

val LazyPagingItems<*>.isSuccess: Boolean
    get() = loadState.refresh is LoadState.NotLoading
