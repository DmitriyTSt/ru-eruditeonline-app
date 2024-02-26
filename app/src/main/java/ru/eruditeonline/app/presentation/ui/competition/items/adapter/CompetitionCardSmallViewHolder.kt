package ru.eruditeonline.app.presentation.ui.competition.items.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.competition.CompetitionItemShort
import ru.eruditeonline.app.databinding.ItemCompetitionCardSmallBinding
import ru.eruditeonline.ui.presentation.ext.inflate

class CompetitionCardSmallViewHolder(
    parent: ViewGroup,
    onItemClick: (CompetitionItemShort) -> Unit,
) : BaseCompetitionViewHolder(parent.inflate(R.layout.item_competition_card_small), onItemClick) {
    private val binding by viewBinding(ItemCompetitionCardSmallBinding::bind)

    override val imageViewImage: ImageView = binding.imageView
    override val imageViewDifficulty: ImageView? = null
    override val textViewTitle: TextView = binding.textViewTitle
    override val textViewAges: TextView? = null
    override val clickableView: View = binding.root
}
