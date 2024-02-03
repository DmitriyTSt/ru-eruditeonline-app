package ru.eruditeonline.app.presentation.ui.dashboard.competition

import android.view.ViewGroup
import android.widget.ImageView
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.competition.CompetitionItemShort
import ru.eruditeonline.app.data.model.main.CompetitionViewType
import ru.eruditeonline.app.presentation.ui.base.BaseAdapter
import ru.eruditeonline.app.presentation.ui.competition.items.adapter.BaseCompetitionViewHolder
import ru.eruditeonline.app.presentation.ui.competition.items.adapter.CompetitionCardSmallViewHolder
import ru.eruditeonline.app.presentation.ui.competition.items.adapter.CompetitionRowBigViewHolder
import javax.inject.Inject

class CompetitionsMainSectionAdapter @Inject constructor() : BaseAdapter<CompetitionItemShort, BaseCompetitionViewHolder>() {

    var viewType: CompetitionViewType = CompetitionViewType.CARD
    lateinit var onItemClick: (CompetitionItemShort, ImageView) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseCompetitionViewHolder {
        return when (viewType) {
            R.layout.item_competition_row_big -> CompetitionRowBigViewHolder(parent, onItemClick)
            R.layout.item_competition_card_small -> CompetitionCardSmallViewHolder(parent, onItemClick)
            else -> throw IllegalStateException("Unsupported view type")
        }
    }

    override fun onBindViewHolder(holder: BaseCompetitionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return when (viewType) {
            CompetitionViewType.ROW -> R.layout.item_competition_row_big
            CompetitionViewType.CARD -> R.layout.item_competition_card_small
        }
    }
}
