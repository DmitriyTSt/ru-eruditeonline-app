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

    /** Вид отображения списка */
    private val _listViewTypeLiveData = MutableLiveData(CompetitionItemsViewType.CARD)
    val listViewTypeLiveData: LiveData<CompetitionItemsViewType> = _listViewTypeLiveData

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

    fun changeListViewType() {
        val oldViewType = (_listViewTypeLiveData.value ?: CompetitionItemsViewType.CARD)
        val newViewType = CompetitionItemsViewType.values().let { types -> types[(oldViewType.ordinal + 1) % types.size] }
        _listViewTypeLiveData.postValue(newViewType)
    }
}
