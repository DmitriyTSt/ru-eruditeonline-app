package ru.eruditeonline.app.presentation.ui.dashboard.tagline

import android.view.ViewGroup
import ru.eruditeonline.app.data.model.main.Tagline
import ru.eruditeonline.app.presentation.ui.base.BaseAdapter
import javax.inject.Inject

class TaglinesAdapter @Inject constructor() : BaseAdapter<Tagline, TaglineViewHolder>() {

    lateinit var onItemClick: (Tagline) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaglineViewHolder {
        return TaglineViewHolder(parent, onItemClick)
    }

    override fun onBindViewHolder(holder: TaglineViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
