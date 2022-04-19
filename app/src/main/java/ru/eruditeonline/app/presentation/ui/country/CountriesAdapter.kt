package ru.eruditeonline.app.presentation.ui.country

import android.view.ViewGroup
import ru.eruditeonline.app.data.model.base.Country
import ru.eruditeonline.app.presentation.ui.base.BaseAdapter
import javax.inject.Inject

class CountriesAdapter @Inject constructor() : BaseAdapter<Country, CountryViewHolder>() {

    lateinit var onItemClick: (Country) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(parent, onItemClick)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
