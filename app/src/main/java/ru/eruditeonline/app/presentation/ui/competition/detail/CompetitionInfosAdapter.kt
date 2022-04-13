package ru.eruditeonline.app.presentation.ui.competition.detail

import android.view.ViewGroup
import ru.eruditeonline.app.presentation.ui.base.BaseAdapter
import javax.inject.Inject

class CompetitionInfosAdapter @Inject constructor() : BaseAdapter<String, CompetitionInfoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionInfoViewHolder {
        return CompetitionInfoViewHolder(parent)
    }

    override fun onBindViewHolder(holder: CompetitionInfoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
