package ru.eruditeonline.app.presentation.ui.competition.items.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.eruditeonline.app.data.model.competition.CompetitionItemShort
import ru.eruditeonline.app.presentation.extension.load
import ru.eruditeonline.app.presentation.extension.setDifficulty

abstract class BaseCompetitionViewHolder(
    itemView: View,
    private val onItemClick: (CompetitionItemShort) -> Unit,
) : RecyclerView.ViewHolder(itemView) {
    abstract val imageViewImage: ImageView
    abstract val imageViewDifficulty: ImageView
    abstract val textViewTitle: TextView
    abstract val textViewAges: TextView
    abstract val clickableView: View

    fun bind(competitionItem: CompetitionItemShort) {
        imageViewImage.load(competitionItem.icon)
        textViewTitle.text = competitionItem.title
        textViewAges.text = competitionItem.ages
        imageViewDifficulty.setDifficulty(competitionItem.difficulty)
        clickableView.setOnClickListener {
            onItemClick(competitionItem)
        }
    }
}