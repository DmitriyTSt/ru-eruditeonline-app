package ru.eruditeonline.app.presentation.ui.competition.detail

import android.annotation.SuppressLint
import android.text.method.LinkMovementMethod
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.ItemCompetitionInfoBinding
import ru.eruditeonline.app.presentation.extension.inflate
import ru.eruditeonline.app.presentation.extension.setTextFromHtml
import ru.eruditeonline.app.presentation.extension.stripUnderlines

class CompetitionInfoViewHolder(
    parent: ViewGroup,
) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_competition_info)) {
    private val binding by viewBinding(ItemCompetitionInfoBinding::bind)

    init {
        binding.root.movementMethod = LinkMovementMethod.getInstance()
    }

    @SuppressLint("SetTextI18n")
    fun bind(info: String) = with(binding.root) {
        setTextFromHtml("• $info")
        stripUnderlines()
    }
}
