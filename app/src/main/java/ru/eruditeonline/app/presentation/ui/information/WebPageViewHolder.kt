package ru.eruditeonline.app.presentation.ui.information

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.base.WebPageItem
import ru.eruditeonline.app.databinding.ItemInformationBinding
import ru.eruditeonline.app.presentation.extension.inflate

class WebPageViewHolder(
    parent: ViewGroup,
    private val onItemCLick: (WebPageItem) -> Unit,
) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_information)) {
    private val binding by viewBinding(ItemInformationBinding::bind)

    fun bind(page: WebPageItem) = with(binding.root) {
        text = page.name
        setOnClickListener {
            onItemCLick(page)
        }
    }
}
