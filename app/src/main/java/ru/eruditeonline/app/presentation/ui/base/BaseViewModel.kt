package ru.eruditeonline.app.presentation.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.eruditeonline.app.presentation.navigation.Destination

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
}
