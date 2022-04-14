package ru.eruditeonline.app.presentation.ui.rating.tab

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.rating.RatingRow
import ru.eruditeonline.app.databinding.ItemRatingRowBinding
import ru.eruditeonline.app.presentation.extension.getColorCompat
import ru.eruditeonline.app.presentation.extension.inflate
import ru.eruditeonline.app.presentation.extension.load

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
        isVisible = oldRank != null && oldRank != rank
        if (oldRank == null) return@with

        val diff = rank - oldRank
        val color = if (diff < 0) {
            R.color.rank_negative
        } else {
            R.color.rank_positive
        }
        val icon = if (diff < 0) {
            R.drawable.ic_rank_negative
        } else {
            R.drawable.ic_rank_positive
        }
        setCompoundDrawablesRelativeWithIntrinsicBounds(icon, 0, 0, 0)
        setTextColor(context.getColorCompat(color))
        text = diff.toString()
    }
}