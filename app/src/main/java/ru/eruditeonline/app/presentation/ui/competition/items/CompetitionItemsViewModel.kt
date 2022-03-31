package ru.eruditeonline.app.presentation.ui.competition.items

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.CombinedLoadStates
import androidx.paging.PagingData
import ru.eruditeonline.app.data.model.LoadState
import ru.eruditeonline.app.data.model.competition.CompetitionItemShort
import ru.eruditeonline.app.domain.usecase.competition.FilterCompetitionsUseCase
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class CompetitionItemsViewModel @Inject constructor(
    private val filterCompetitionsUseCase: FilterCompetitionsUseCase,
) : BaseViewModel() {
    /** Данные пагинации */
    private val _pagingDataLiveData = MutableLiveData<PagingData<CompetitionItemShort>>()
    val pagingDataLiveData: LiveData<PagingData<CompetitionItemShort>> = _pagingDataLiveData

    /** Состояние загрузки пагинации */
    private val _pagingStateLiveData = MutableLiveData<LoadState<Unit>>()
    val pagingStateLiveData: LiveData<LoadState<Unit>> = _pagingStateLiveData

    fun loadCompetitions(
        query: String?,
    ) {
        _pagingDataLiveData.launchPagingData {
            filterCompetitionsUseCase.executeFlow(
                FilterCompetitionsUseCase.Params(
                    query = query.orEmpty(),
                    ageIds = emptyList(),
                    subjectIds = emptyList(),
                ) { filters ->
                    // TODO save filters
                }
            )
        }
    }

    fun bindPagingState(loadState: CombinedLoadStates) {
        _pagingStateLiveData.bindPagingState(loadState)
    }
}
