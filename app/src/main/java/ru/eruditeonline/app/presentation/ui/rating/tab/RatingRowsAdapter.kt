package ru.eruditeonline.app.presentation.ui.rating.tab

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.eruditeonline.app.data.model.rating.RatingRow
import ru.eruditeonline.app.presentation.paging.DiffUtilItemCallbackFactory
import javax.inject.Inject

class RatingRowsAdapter @Inject constructor(
    diffUtilItemCallbackFactory: DiffUtilItemCallbackFactory,
) : ListAdapter<RatingRow, RatingRowViewHolder>(diffUtilItemCallbackFactory.create()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingRowViewHolder {
        return RatingRowViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RatingRowViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}