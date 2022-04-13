package ru.eruditeonline.app.presentation.ui.competition.detail

import android.view.ViewGroup
import ru.eruditeonline.app.data.model.competition.TestAgeGroup
import ru.eruditeonline.app.presentation.ui.base.BaseAdapter
import javax.inject.Inject

class CompetitionTestButtonsAdapter @Inject constructor() : BaseAdapter<TestAgeGroup, CompetitionTestButtonViewHolder>() {

    lateinit var onItemClick: (TestAgeGroup) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionTestButtonViewHolder {
        return CompetitionTestButtonViewHolder(parent, onItemClick)
    }

    override fun onBindViewHolder(holder: CompetitionTestButtonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
