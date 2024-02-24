package ru.eruditeonline.app.presentation.extension

import androidx.lifecycle.MutableLiveData
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.eruditeonline.architecture.presentation.model.LoadableState

fun <T : Any> MutableLiveData<PagingData<T>>.launchPagingData(
    viewModelScope: CoroutineScope,
    block: () -> Flow<PagingData<T>>,
): Job = viewModelScope.launch {
    block()
        .cachedIn(viewModelScope)
        .collectLatest { this@launchPagingData.postValue(it) }
}

fun MutableLiveData<LoadableState<Unit>>.bindPagingState(loadState: CombinedLoadStates) {
    when (loadState.source.refresh) {
        // Only show the list if refresh succeeds.
        is LoadState.NotLoading -> {
            if (this.value != null && this.value !is LoadableState.Success) {
                // никогда не пускаем success первым значением в лайвдату,
                // так как пагинация до начала загрузки находится в состоянии NotLoading
                // также нет смысла слать success, если последнее значение success
                this.postValue(LoadableState.Success(Unit))
            }
        }
        // Show loading spinner during initial load or refresh.
        is LoadState.Loading -> this.postValue(LoadableState.Loading())
        // Show the retry state if initial load or refresh fails.
        is LoadState.Error -> {
            val throwable = (loadState.source.refresh as LoadState.Error).error
            this.postValue(LoadableState.Error(throwable))
        }
    }
}
