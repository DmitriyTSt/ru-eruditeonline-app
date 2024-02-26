package ru.eruditeonline.app.presentation.ui.information

import android.view.ViewGroup
import ru.eruditeonline.app.data.model.base.WebPageItem
import ru.eruditeonline.ui.presentation.base.BaseAdapter
import javax.inject.Inject

class WebPagesAdapter @Inject constructor() : BaseAdapter<WebPageItem, WebPageViewHolder>() {

    lateinit var onItemClick: (WebPageItem) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebPageViewHolder {
        return WebPageViewHolder(parent, onItemClick)
    }

    override fun onBindViewHolder(holder: WebPageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
