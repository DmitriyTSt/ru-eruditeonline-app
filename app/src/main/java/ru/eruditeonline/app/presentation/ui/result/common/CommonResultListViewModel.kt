package ru.eruditeonline.app.presentation.ui.result.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.CombinedLoadStates
import androidx.paging.PagingData
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.data.model.test.TestCommonResultRow
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class CommonResultListViewModel @Inject constructor(
    private val commonResultsPagingFlowFactory: CommonResultsPagingFlowFactory,
    private val destinations: CommonResultListDestinations,
) : BaseViewModel() {

    /** Пагинация результатов */
    private val _resultsLiveData = MutableLiveData<PagingData<TestCommonResultRow>>()
    val resultsLiveData: LiveData<PagingData<TestCommonResultRow>> = _resultsLiveData

    /** Состояние загрузки пагинации */
    private val _pagingStateLiveData = MutableLiveData<LoadableState<Unit>>()
    val pagingStateLiveData: LiveData<LoadableState<Unit>> = _pagingStateLiveData

    fun load() {
        _resultsLiveData.launchPagingData { commonResultsPagingFlowFactory.create(Unit) }
    }

    fun bindPagingState(loadState: CombinedLoadStates) {
        _pagingStateLiveData.bindPagingState(loadState)
    }

    fun openCompetition(result: TestCommonResultRow) {
        navigate(destinations.competitionItem(result.competitionId))
    }
}
