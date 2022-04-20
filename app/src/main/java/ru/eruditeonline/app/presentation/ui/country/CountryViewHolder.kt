package ru.eruditeonline.app.presentation.ui.country

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.base.Country
import ru.eruditeonline.app.databinding.ItemCountryBinding
import ru.eruditeonline.app.presentation.extension.inflate
import ru.eruditeonline.app.presentation.extension.load

class CountryViewHolder(
    parent: ViewGroup,
    private val onItemClick: (Country) -> Unit,
) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_country)) {
    private val binding by viewBinding(ItemCountryBinding::bind)

    fun bind(country: Country) = with(binding) {
        imageViewCountry.load(country.image)
        textViewCountry.text = country.name
        root.setOnClickListener {
            onItemClick(country)
        }
    }
}
