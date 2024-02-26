package ru.eruditeonline.app.presentation.ui.rating.tab.row

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.rating.RatingRow
import ru.eruditeonline.app.databinding.ItemRatingRowBinding
import ru.eruditeonline.ui.presentation.ext.getColorCompat
import ru.eruditeonline.ui.presentation.ext.inflate
import ru.eruditeonline.ui.presentation.ext.load

class RatingRowViewHolder(
    parent: ViewGroup,
) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_rating_row)) {
    private val binding by viewBinding(ItemRatingRowBinding::bind)

    @SuppressLint("SetTextI18n")
    fun bind(ratingRow: RatingRow) = with(binding) {
        textViewPlace.text = "${ratingRow.rank}."
        bindRankChanges(ratingRow.rank, ratingRow.oldRank)
        textViewScore.text = ratingRow.score.toString()
        imageViewCountry.load(ratingRow.countryIcon)
        textViewUsername.text = ratingRow.username
    }

    private fun bindRankChanges(rank: Int, oldRank: Int?) = with(binding.textViewRatingChanges) {
        isVisible = oldRank != null && oldRank != rank || oldRank == 0
        if (oldRank == null) return@with

        val diff = oldRank - rank
        val color = if (diff > 0 || oldRank == 0) {
            R.color.green
        } else {
            R.color.red
        }
        val icon = if (diff > 0 || oldRank == 0) {
            R.drawable.ic_rank_positive
        } else {
            R.drawable.ic_rank_negative
        }
        setCompoundDrawablesRelativeWithIntrinsicBounds(icon, 0, 0, 0)
        setTextColor(context.getColorCompat(color))
        text = if (oldRank == 0) {
            context.getString(R.string.new_rank_label)
        } else {
            if (diff > 0) "+$diff" else diff.toString()
        }
    }
}
