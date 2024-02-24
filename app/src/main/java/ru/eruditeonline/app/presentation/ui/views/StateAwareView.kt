package ru.eruditeonline.app.presentation.ui.views

import ru.eruditeonline.architecture.presentation.model.LoadableState

interface StateAwareView {
    fun setState(result: LoadableState<*>)
}
