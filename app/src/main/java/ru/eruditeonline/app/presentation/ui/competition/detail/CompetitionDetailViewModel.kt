package ru.eruditeonline.app.presentation.ui.competition.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.eruditeonline.app.data.model.competition.CompetitionItem
import ru.eruditeonline.app.data.model.competition.TestAgeGroup
import ru.eruditeonline.app.domain.usecase.base.executeFlow
import ru.eruditeonline.app.domain.usecase.competition.GetCompetitionItemUseCase
import ru.eruditeonline.architecture.presentation.base.BaseViewModel
import ru.eruditeonline.architecture.presentation.model.LoadableState
import javax.inject.Inject

class CompetitionDetailViewModel @Inject constructor(
    private val getCompetitionItemUseCase: GetCompetitionItemUseCase,
    private val destinations: CompetitionDetailDestinations,
) : BaseViewModel() {
    /** Конкурс */
    private val _competitionItemLiveData = MutableLiveData<LoadableState<CompetitionItem>>()
    val competitionItemLiveData: LiveData<LoadableState<CompetitionItem>> = _competitionItemLiveData

    fun loadCompetitionItem(id: Int) {
        _competitionItemLiveData.launchLoadData(getCompetitionItemUseCase.executeFlow(GetCompetitionItemUseCase.Params(id)))
    }

    fun openTest(test: TestAgeGroup) {
        navigate(destinations.testPassage(test.id))
    }
}
