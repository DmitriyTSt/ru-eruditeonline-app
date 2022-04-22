package ru.eruditeonline.app.presentation.ui.webpage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.map
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

    /** История веб вью */
    private val backStack = ArrayDeque<WebPage>()

    fun loadWebPage(path: String, skipStack: Boolean = false) {
        _webPageLiveData.launchLoadData(getWebPageUseCase.executeFlow(GetWebPageUseCase.Params(path)).map { state ->
            state.doOnSuccess { page ->
                if (!skipStack) {
                    backStack.addLast(page)
                }
            }
            state
        })
    }

    fun onBackPressed() {
        backStack.removeLast()
        if (backStack.isNotEmpty()) {
            _webPageLiveData.postValue(LoadableState.Success(backStack.last()))
        } else {
            navigateBack()
        }
    }
}