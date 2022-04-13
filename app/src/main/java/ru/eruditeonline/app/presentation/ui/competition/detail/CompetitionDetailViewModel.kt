package ru.eruditeonline.app.presentation.ui.competition.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.data.model.competition.CompetitionItem
import ru.eruditeonline.app.domain.usecase.competition.GetCompetitionItemUseCase
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class CompetitionDetailViewModel @Inject constructor(
    private val getCompetitionItemUseCase: GetCompetitionItemUseCase,
) : BaseViewModel() {
    /** Конкурс */
    private val _competitionItemLiveData = MutableLiveData<LoadableState<CompetitionItem>>()
    val competitionItemLiveData: LiveData<LoadableState<CompetitionItem>> = _competitionItemLiveData

    fun loadCompetitionItem(id: Int) {
        _competitionItemLiveData.launchLoadData(getCompetitionItemUseCase.executeFlow(GetCompetitionItemUseCase.Params(id)))
    }
}
