package ru.eruditeonline.app.presentation.ui.diploma

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.base.Diploma
import ru.eruditeonline.app.databinding.ItemDiplomaBinding
import ru.eruditeonline.ui.presentation.ext.inflate
import ru.eruditeonline.ui.presentation.ext.load

class DiplomaViewHolder(
    parent: ViewGroup,
    private val onItemClick: (Diploma) -> Unit,
) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_diploma)) {
    private val binding by viewBinding(ItemDiplomaBinding::bind)

    fun bind(diploma: Diploma) = with(binding) {
        imageViewDiploma.load(diploma.image, placeHolderRes = R.drawable.img_diploma_placeholder)
        root.setOnClickListener {
            onItemClick(diploma)
        }
    }
}
