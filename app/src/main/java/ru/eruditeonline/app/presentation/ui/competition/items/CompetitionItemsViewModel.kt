package ru.eruditeonline.app.presentation.ui.competition.items

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.CombinedLoadStates
import androidx.paging.PagingData
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.data.model.competition.CompetitionFilters
import ru.eruditeonline.app.data.model.competition.CompetitionItemShort
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class CompetitionItemsViewModel @Inject constructor(
    private val competitionItemsPagingFlowFactory: CompetitionItemsPagingFlowFactory,
    private val destinations: CompetitionItemsDestinations,
) : BaseViewModel() {
    /** Данные пагинации */
    private val _pagingDataLiveData = MutableLiveData<PagingData<CompetitionItemShort>>()
    val pagingDataLiveData: LiveData<PagingData<CompetitionItemShort>> = _pagingDataLiveData

    /** Состояние загрузки пагинации */
    private val _pagingStateLiveData = MutableLiveData<LoadableState<Unit>>()
    val pagingStateLiveData: LiveData<LoadableState<Unit>> = _pagingStateLiveData

    /** Вид отображения списка */
    private val _listViewTypeLiveData = MutableLiveData(CompetitionItemsViewType.CARD)
    val listViewTypeLiveData: LiveData<CompetitionItemsViewType> = _listViewTypeLiveData

    /** Фильтры */
    private val _filtersLiveData = MutableLiveData<CompetitionFilters>()
    val filtersLiveData: LiveData<CompetitionFilters> = _filtersLiveData

    fun loadCompetitions(
        query: String?,
        ageIds: List<String> = emptyList(),
        subjectIds: List<String> = emptyList(),
    ) {
        _pagingStateLiveData.postValue(LoadableState.Loading())
        _pagingDataLiveData.launchPagingData {
            competitionItemsPagingFlowFactory.create(
                CompetitionItemsPagingFlowFactory.Params(
                    query = query.orEmpty(),
                    ageIds = ageIds,
                    subjectIds = subjectIds,
                ) { filters ->
                    _filtersLiveData.postValue(filters)
                }
            )
        }
    }

    fun bindPagingState(loadState: CombinedLoadStates) {
        _pagingStateLiveData.bindPagingState(loadState)
    }

    fun changeListViewType() {
        val oldViewType = (_listViewTypeLiveData.value ?: CompetitionItemsViewType.CARD)
        val newViewType = CompetitionItemsViewType.values().let { types -> types[(oldViewType.ordinal + 1) % types.size] }
        _listViewTypeLiveData.postValue(newViewType)
    }

    fun openFilter(filter: CompetitionFilters) {
        navigate(destinations.filter(filter))
    }

    fun openCompetition(item: CompetitionItemShort) {
        navigate(destinations.competitionItem(item.id))
    }
}
