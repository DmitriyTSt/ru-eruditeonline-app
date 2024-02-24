package ru.eruditeonline.architecture.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import ru.eruditeonline.architecture.presentation.model.LoadableState
import ru.eruditeonline.architecture.presentation.navigation.Destination

abstract class BaseViewModel : ViewModel() {
    /** Навигации */
    private val _destinationLiveEvent = SingleLiveEvent<Destination>()
    val destinationLiveEvent: LiveData<Destination> = _destinationLiveEvent

    fun navigate(destination: Destination) {
        _destinationLiveEvent.postValue(destination)
    }

    fun navigateBack() {
        _destinationLiveEvent.postValue(Destination.Back)
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
                emit(LoadableState.Error(t))
            }
        }.collect { result ->
            this@launchLoadData.postValue(result)
        }
    }
}
