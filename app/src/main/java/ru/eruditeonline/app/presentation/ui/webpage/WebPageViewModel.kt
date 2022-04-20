package ru.eruditeonline.app.presentation.ui.webpage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.data.model.base.WebPage
import ru.eruditeonline.app.domain.usecase.GetWebPageUseCase
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class WebPageViewModel @Inject constructor(
    private val getWebPageUseCase: GetWebPageUseCase,
) : BaseViewModel() {
    /** Веб-страница */
    private val _webPageLiveData = MutableLiveData<LoadableState<WebPage>>()
    val webPageLiveData: LiveData<LoadableState<WebPage>> = _webPageLiveData

    fun loadWebPage(path: String) {
        _webPageLiveData.launchLoadData(getWebPageUseCase.executeFlow(GetWebPageUseCase.Params(path)))
    }
}