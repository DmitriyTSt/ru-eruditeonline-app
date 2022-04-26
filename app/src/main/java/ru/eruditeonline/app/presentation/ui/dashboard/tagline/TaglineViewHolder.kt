package ru.eruditeonline.app.presentation.ui.dashboard.tagline

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.main.Tagline
import ru.eruditeonline.app.databinding.ItemTaglineBinding
import ru.eruditeonline.app.presentation.extension.getColorFromAttribute
import ru.eruditeonline.app.presentation.extension.inflate
import ru.eruditeonline.app.presentation.extension.load

class TaglineViewHolder(
    parent: ViewGroup,
    private val onItemClick: (Tagline) -> Unit,
) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_tagline)) {
    private val binding by viewBinding(ItemTaglineBinding::bind)

    fun bind(tagline: Tagline) = with(binding) {
        imageView.load(tagline.icon)
        textViewTitle.text = tagline.title
        textViewDescription.text = tagline.text
        textViewTitle.setTextColor(tagline.titleColor ?: root.context.getColorFromAttribute(R.attr.colorOnSurface))
        root.setOnClickListener {
            onItemClick(tagline)
        }
    }
}
