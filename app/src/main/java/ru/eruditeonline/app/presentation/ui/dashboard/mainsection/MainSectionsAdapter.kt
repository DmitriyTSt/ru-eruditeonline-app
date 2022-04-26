package ru.eruditeonline.app.presentation.ui.dashboard.mainsection

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.competition.CompetitionItemShort
import ru.eruditeonline.app.data.model.main.MainSection
import ru.eruditeonline.app.data.model.main.Tagline
import ru.eruditeonline.app.presentation.ui.base.BaseAdapter
import javax.inject.Inject

class MainSectionsAdapter @Inject constructor() : BaseAdapter<MainSection, RecyclerView.ViewHolder>() {

    lateinit var onCompetitionItemClick: (CompetitionItemShort) -> Unit
    lateinit var onTaglineClick: (Tagline) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_main_section_competitions -> MainSectionCompetitionsViewHolder(parent, onCompetitionItemClick)
            R.layout.item_main_section_taglines -> MainSectionTaglinesViewHolder(parent, onTaglineClick)
            else -> throw IllegalStateException("Unsupported view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (getItemViewType(position)) {
            R.layout.item_main_section_competitions -> {
                (holder as MainSectionCompetitionsViewHolder).bind(item as MainSection.CompetitionsBlock)
            }
            R.layout.item_main_section_taglines -> {
                (holder as MainSectionTaglinesViewHolder).bind(item as MainSection.TaglineBlock)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is MainSection.CompetitionsBlock -> R.layout.item_main_section_competitions
            is MainSection.TaglineBlock -> R.layout.item_main_section_taglines
        }
    }
}
