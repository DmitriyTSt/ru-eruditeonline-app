package ru.eruditeonline.app.presentation.ui.competition.filter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.eruditeonline.app.data.model.competition.FilterItem
import ru.eruditeonline.app.presentation.paging.DiffUtilItemCallbackFactory
import ru.eruditeonline.app.presentation.ui.competition.filter.model.FilterGroup
import javax.inject.Inject

class FilterGroupsAdapter @Inject constructor(
    private val diffUtilItemCallbackFactory: DiffUtilItemCallbackFactory,
) : ListAdapter<FilterGroup, FilterGroupViewHolder>(diffUtilItemCallbackFactory.create()) {

    lateinit var onFilterItemClick: (FilterItem) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterGroupViewHolder {
        return FilterGroupViewHolder(parent, diffUtilItemCallbackFactory, onFilterItemClick)
    }

    override fun onBindViewHolder(holder: FilterGroupViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
