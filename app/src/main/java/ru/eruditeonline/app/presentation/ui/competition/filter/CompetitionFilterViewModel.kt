package ru.eruditeonline.app.presentation.ui.competition.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.eruditeonline.app.data.model.competition.CompetitionFilters
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import ru.eruditeonline.app.presentation.ui.base.SingleLiveEvent
import ru.eruditeonline.app.presentation.ui.competition.filter.model.FilterGroup
import ru.eruditeonline.app.presentation.ui.competition.filter.model.FilterGroupId
import ru.eruditeonline.app.presentation.ui.competition.filter.model.FilterRequest
import javax.inject.Inject

class CompetitionFilterViewModel @Inject constructor() : BaseViewModel() {

    /** Инициализация фильтров */
    private val _filtersLiveData = MutableLiveData<List<FilterGroup>>()
    val filtersLiveData: LiveData<List<FilterGroup>> = _filtersLiveData

    /** Применение фильтра */
    private val _applyFilterLiveEvent = SingleLiveEvent<FilterRequest>()
    val applyFilterLiveEvent: LiveData<FilterRequest> = _applyFilterLiveEvent

    fun initFilters(filters: CompetitionFilters) {
        _filtersLiveData.postValue(
            listOf(
                FilterGroup(
                    FilterGroupId.AGE,
                    filters.ages,
                ),
                FilterGroup(
                    FilterGroupId.SUBJECT,
                    filters.subjects,
                ),
            )
        )
    }

    fun resetFilters() {
        val oldFilters = _filtersLiveData.value.orEmpty()
        _filtersLiveData.postValue(
            oldFilters.map { group ->
                group.copy(filters = group.filters.map { it.copy(selected = false) })
            }
        )
    }

    fun applyFilter(groups: List<FilterGroup>) {
        val ageIds = groups.find { it.id == FilterGroupId.AGE }?.filters?.filter { it.selected }?.map { it.id }.orEmpty()
        val subjectIds = groups.find { it.id == FilterGroupId.SUBJECT }?.filters?.filter { it.selected }?.map { it.id }.orEmpty()
        _applyFilterLiveEvent.postValue(
            FilterRequest(
                ageIds = ageIds,
                subjectIds = subjectIds,
            )
        )
    }
}
