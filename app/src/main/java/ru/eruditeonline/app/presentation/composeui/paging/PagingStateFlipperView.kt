package ru.eruditeonline.app.presentation.composeui.paging

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import ru.eruditeonline.app.data.model.parseError
import ru.eruditeonline.app.presentation.composeui.views.StateFlipperViewErrorView
import ru.eruditeonline.app.presentation.composeui.views.StateFlipperViewLoadingView

@Composable
fun PagingStateFlipperView(
    items: LazyPagingItems<*>,
    onRetryClick: () -> Unit,
    modifier: Modifier,
    content: @Composable () -> Unit,
) {
    Box(modifier) {
        when (items.loadState.refresh) {
            is LoadState.Loading -> {
                StateFlipperViewLoadingView()
            }
            is LoadState.Error -> {
                val throwable = (items.loadState.refresh as LoadState.Error).error
                StateFlipperViewErrorView(error = throwable.parseError(), onRetryClick = onRetryClick)
            }
            is LoadState.NotLoading -> {
                content()
            }
        }
    }
}
