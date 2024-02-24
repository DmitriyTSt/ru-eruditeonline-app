package ru.eruditeonline.app.presentation.ui.information

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.eruditeonline.app.data.model.base.WebPageItem
import ru.eruditeonline.app.domain.usecase.GetWebPagesUseCase
import ru.eruditeonline.app.domain.usecase.base.executeFlow
import ru.eruditeonline.architecture.presentation.base.BaseViewModel
import ru.eruditeonline.architecture.presentation.model.LoadableState
import javax.inject.Inject

class InformationViewModel @Inject constructor(
    private val getWebPagesUseCase: GetWebPagesUseCase,
    private val destinations: InformationDestinations,
) : BaseViewModel() {
    /** Веб-страницы */
    private val _webPagesLiveData = MutableLiveData<LoadableState<List<WebPageItem>>>()
    val webPagesLiveData: LiveData<LoadableState<List<WebPageItem>>> = _webPagesLiveData

    fun loadWebPages() {
        _webPagesLiveData.launchLoadData(getWebPagesUseCase.executeFlow(Unit))
    }

    fun openWebPage(page: WebPageItem) {
        navigate(destinations.webPage(page.path))
    }
}
