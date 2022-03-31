package ru.eruditeonline.app.presentation.ui.competition.items.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import ru.eruditeonline.app.data.model.competition.CompetitionItemShort
import ru.eruditeonline.app.presentation.paging.DiffUtilItemCallbackFactory
import javax.inject.Inject

class CompetitionItemsAdapter @Inject constructor(
    diffUtilItemCallbackFactory: DiffUtilItemCallbackFactory,
) : PagingDataAdapter<CompetitionItemShort, BaseCompetitionViewHolder>(diffUtilItemCallbackFactory.create()) {

    lateinit var onItemClick: (CompetitionItemShort) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseCompetitionViewHolder {
        return CompetitionCardViewHolder(parent, onItemClick)
    }

    override fun onBindViewHolder(holder: BaseCompetitionViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}