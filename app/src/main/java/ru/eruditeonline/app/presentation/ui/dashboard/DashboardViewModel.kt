package ru.eruditeonline.app.presentation.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.data.model.competition.CompetitionItemShort
import ru.eruditeonline.app.data.model.main.MainSection
import ru.eruditeonline.app.domain.usecase.main.GetMainSectionsUseCase
import ru.eruditeonline.app.presentation.managers.DeepLinkManager
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val getMainSectionsUseCase: GetMainSectionsUseCase,
    private val destinations: DashboardDestinations,
    private val deepLinkManager: DeepLinkManager,
) : BaseViewModel() {
    /** Блоки главной страницы */
    private val _mainSectionsLiveData = MutableLiveData<LoadableState<List<MainSection>>>()
    val mainSectionsLiveData: LiveData<LoadableState<List<MainSection>>> = _mainSectionsLiveData

    fun loadMainSections() {
        _mainSectionsLiveData.launchLoadData(getMainSectionsUseCase.executeFlow(Unit))
    }

    fun openCompetition(item: CompetitionItemShort) {
        navigate(destinations.competitionItem(item.id))
    }

    fun resolveDeepLink() {
        deepLinkManager.resolveDeepLink()?.let { navigate(it) }
    }
}
