package ru.eruditeonline.app.presentation.ui.views

import ru.eruditeonline.app.data.model.LoadableState

interface StateAwareView {
    fun setState(result: LoadableState<*>)
}
