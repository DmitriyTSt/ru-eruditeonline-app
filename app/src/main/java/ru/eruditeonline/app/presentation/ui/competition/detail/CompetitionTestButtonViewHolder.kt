package ru.eruditeonline.app.presentation.ui.competition.detail

import android.content.res.ColorStateList
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.competition.TestAgeGroup
import ru.eruditeonline.app.databinding.ItemCompetitionTestButtonBinding
import ru.eruditeonline.ui.presentation.ext.getColorFromAttribute
import ru.eruditeonline.ui.presentation.ext.inflate

class CompetitionTestButtonViewHolder(
    parent: ViewGroup,
    private val onItemClick: (TestAgeGroup) -> Unit,
) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_competition_test_button)) {
    private val binding by viewBinding(ItemCompetitionTestButtonBinding::bind)

    fun bind(test: TestAgeGroup) = with(binding.root) {
        text = test.title
        backgroundTintList = ColorStateList.valueOf(
            test.color ?: context.getColorFromAttribute(android.R.attr.colorPrimary)
        )
        setOnClickListener {
            onItemClick(test)
        }
    }
}
