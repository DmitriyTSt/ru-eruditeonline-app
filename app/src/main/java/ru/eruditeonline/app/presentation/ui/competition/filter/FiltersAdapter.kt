package ru.eruditeonline.app.presentation.ui.competition.filter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.eruditeonline.app.data.model.competition.FilterItem
import ru.eruditeonline.app.presentation.paging.DiffUtilItemCallbackFactory
import javax.inject.Inject

class FiltersAdapter @Inject constructor(
    diffUtilItemCallbackFactory: DiffUtilItemCallbackFactory,
) : ListAdapter<FilterItem, FilterViewHolder>(diffUtilItemCallbackFactory.create()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        return FilterViewHolder(parent)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
