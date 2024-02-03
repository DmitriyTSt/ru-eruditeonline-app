package ru.eruditeonline.app.presentation.ui.competition.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.combine
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.data.model.competition.CompetitionItem
import ru.eruditeonline.app.data.model.competition.TestAgeGroup
import ru.eruditeonline.app.domain.usecase.competition.GetCompetitionItemUseCase
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import ru.eruditeonline.app.presentation.ui.base.SingleLiveEvent
import javax.inject.Inject

class CompetitionDetailViewModel @Inject constructor(
    private val getCompetitionItemUseCase: GetCompetitionItemUseCase,
    private val destinations: CompetitionDetailDestinations,
) : BaseViewModel() {

    /** Успех выполнения анимации перехода на экран */
    private val _transitionAnimationLiveEvent = SingleLiveEvent<Boolean>()

    /** Конкурс */
    private val _competitionItemLiveData = MutableLiveData<LoadableState<CompetitionItem>>()
    val competitionItemLiveData: LiveData<LoadableState<CompetitionItem>> =
        _competitionItemLiveData.asFlow().combine(_transitionAnimationLiveEvent.asFlow()) { state, animationEnded ->
            if (animationEnded) {
                state
            } else {
                LoadableState.Loading()
            }
        }.asLiveData()

    fun setAnimationEnd() {
        _transitionAnimationLiveEvent.postValue(true)
    }

    fun loadCompetitionItem(id: Int) {
        _competitionItemLiveData.launchLoadData(getCompetitionItemUseCase.executeFlow(GetCompetitionItemUseCase.Params(id)))
    }

    fun openTest(test: TestAgeGroup) {
        navigate(destinations.testPassage(test.id))
    }
}
