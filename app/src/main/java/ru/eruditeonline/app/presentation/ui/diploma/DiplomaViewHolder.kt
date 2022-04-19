package ru.eruditeonline.app.presentation.ui.diploma

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.base.Diploma
import ru.eruditeonline.app.databinding.ItemDiplomaBinding
import ru.eruditeonline.app.presentation.extension.inflate
import ru.eruditeonline.app.presentation.extension.load

class DiplomaViewHolder(
    parent: ViewGroup,
    private val onItemClick: (Diploma) -> Unit,
) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_diploma)) {
    private val binding by viewBinding(ItemDiplomaBinding::bind)

    fun bind(diploma: Diploma) = with(binding) {
        imageViewDiploma.load(diploma.image)
        root.setOnClickListener {
            onItemClick(diploma)
        }
    }
}
