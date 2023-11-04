package ru.eruditeonline.app.presentation.ui.competition.filter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.competition.FilterItem
import ru.eruditeonline.app.databinding.ItemFilterBinding
import ru.eruditeonline.app.presentation.extension.inflate

class FilterViewHolder(
    parent: ViewGroup,
    private val onItemClick: (FilterItem) -> Unit,
) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_filter)) {
    private val binding by viewBinding(ItemFilterBinding::bind)

    fun bind(filterItem: FilterItem) = with(binding.root) {
        text = filterItem.title
        isChecked = filterItem.selected
        setOnClickListener {
            onItemClick(filterItem)
        }
    }
}
