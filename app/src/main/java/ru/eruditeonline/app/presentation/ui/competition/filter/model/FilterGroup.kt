package ru.eruditeonline.app.presentation.ui.competition.filter.model

import ru.eruditeonline.app.data.model.Similarable
import ru.eruditeonline.app.data.model.competition.FilterItem

data class FilterGroup(
    val id: FilterGroupId,
    val filters: List<FilterItem>,
) : Similarable<FilterGroup> {
    override fun areItemsTheSame(other: FilterGroup): Boolean {
        return this.id == other.id
    }

    override fun areContentsTheSame(other: FilterGroup): Boolean {
        return this == other
    }
}
