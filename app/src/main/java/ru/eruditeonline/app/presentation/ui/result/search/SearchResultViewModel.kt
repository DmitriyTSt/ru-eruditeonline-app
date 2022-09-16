package ru.eruditeonline.app.presentation.ui.result.search

import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class SearchResultViewModel @Inject constructor(
    private val destinations: SearchResultDestinations,
) : BaseViewModel() {

    fun search(text: String) {
        navigate(destinations.resultList(text))
    }
}
