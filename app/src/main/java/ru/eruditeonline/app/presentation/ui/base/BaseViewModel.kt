package ru.eruditeonline.app.presentation.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.presentation.navigation.Destination
import androidx.paging.LoadState as PagingLoadState

abstract class BaseViewModel : ViewModel() {
    /** Навигации */
    private val _destinationChannel = Channel<Destination>()
    val destinationChannel = _destinationChannel.receiveAsFlow()

    private var isCallOperationProcessed = false

    fun callOperations(block: () -> Unit) {
        if (!isCallOperationProcessed) {
            block()
            isCallOperationProcessed = true
        }
    }

    fun navigate(destination: Destination) {
        viewModelScope.launch { _destinationChannel.send(destination) }
    }

    fun navigateBack() {
        viewModelScope.launch { _destinationChannel.send(Destination.Back) }
    }

    protected fun <T> MutableLiveData<LoadableState<T>>.launchLoadData(
        block: Flow<LoadableState<T>>,
    ): Job = viewModelScope.launch {
        block.collect { result ->
            this@launchLoadData.postValue(result)
        }
    }

    protected fun <T> SingleLiveEvent<LoadableState<T>>.launchLoadData(
        block: Flow<LoadableState<T>>,
    ): Job = viewModelScope.launch {
        block.collect { result ->
            this@launchLoadData.postValue(result)
        }
    }

    protected fun <T> SingleLiveEvent<LoadableState<T>>.launchLoadData(
        block: suspend () -> T,
    ): Job = viewModelScope.launch {
        flow {
            try {
                emit(LoadableState.Loading())
                val result = block()
                emit(LoadableState.Success(result))
            } catch (t: Throwable) {
                println("START_FLOW catch $t")
                emit(LoadableState.Error(t))
            }
        }.collect { result ->
            this@launchLoadData.postValue(result)
        }
    }

    protected fun <T : Any> MutableLiveData<PagingData<T>>.launchPagingData(
        block: () -> Flow<PagingData<T>>,
    ): Job = viewModelScope.launch {
        block()
            .cachedIn(viewModelScope)
            .collectLatest { this@launchPagingData.postValue(it) }
    }

    protected fun MutableLiveData<LoadableState<Unit>>.bindPagingState(loadState: CombinedLoadStates) {
        when (loadState.source.refresh) {
            // Only show the list if refresh succeeds.
            is PagingLoadState.NotLoading -> {
                if (this.value != null && this.value !is LoadableState.Success) {
                    // никогда не пускаем success первым значением в лайвдату,
                    // так как пагинация до начала загрузки находится в состоянии NotLoading
                    // также нет смысла слать success, если последнее значение success
                    this.postValue(LoadableState.Success(Unit))
                }
            }
            // Show loading spinner during initial load or refresh.
            is PagingLoadState.Loading -> this.postValue(LoadableState.Loading())
            // Show the retry state if initial load or refresh fails.
            is PagingLoadState.Error -> {
                val throwable = (loadState.source.refresh as PagingLoadState.Error).error
                this.postValue(LoadableState.Error(throwable))
            }
        }
    }
}
