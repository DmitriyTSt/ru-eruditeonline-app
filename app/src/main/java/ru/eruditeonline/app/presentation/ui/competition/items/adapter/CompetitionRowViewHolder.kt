package ru.eruditeonline.app.presentation.ui.competition.items.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.competition.CompetitionItemShort
import ru.eruditeonline.app.databinding.ItemCompetitionRowBinding
import ru.eruditeonline.app.presentation.extension.inflate

class CompetitionRowViewHolder(
    parent: ViewGroup,
    onItemClick: (CompetitionItemShort) -> Unit,
) : BaseCompetitionViewHolder(parent.inflate(R.layout.item_competition_row), onItemClick) {
    private val binding by viewBinding(ItemCompetitionRowBinding::bind)

    override val imageViewImage: ImageView
        get() = binding.imageViewImage
    override val imageViewDifficulty: ImageView
        get() = binding.imageViewDifficulty
    override val textViewTitle: TextView
        get() = binding.textViewTitle
    override val textViewAges: TextView
        get() = binding.textViewAges
    override val clickableView: View
        get() = binding.root
}
