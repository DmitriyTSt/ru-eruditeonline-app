package ru.eruditeonline.app.presentation.ui.dashboard.mainsection

import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.competition.CompetitionItemShort
import ru.eruditeonline.app.data.model.main.CompetitionViewType
import ru.eruditeonline.app.data.model.main.MainSection
import ru.eruditeonline.app.databinding.ItemMainSectionCompetitionsBinding
import ru.eruditeonline.app.presentation.extension.addDefaultGridSpaceItemDecoration
import ru.eruditeonline.app.presentation.extension.addLinearSpaceItemDecoration
import ru.eruditeonline.app.presentation.extension.inflate
import ru.eruditeonline.app.presentation.ui.dashboard.competition.CompetitionsMainSectionAdapter
import ru.eruditeonline.app.presentation.ui.views.RecycledViewHolder
import ru.eruditeonline.app.presentation.ui.views.ScrollStateHolder

private const val GRID_SPAN_COUNT = 2

class MainSectionCompetitionsViewHolder(
    parent: ViewGroup,
    onItemClick: (CompetitionItemShort) -> Unit,
    private val scrollStateHolder: ScrollStateHolder?,
) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_main_section_competitions)),
    ScrollStateHolder.ScrollStateKeyProvider,
    RecycledViewHolder {

    private val binding by viewBinding(ItemMainSectionCompetitionsBinding::bind)

    private val competitionsAdapter = CompetitionsMainSectionAdapter()
    private val snapHelper = PagerSnapHelper()

    /** Ключ, по которому восстанавливается состояние вложенного скролла в этом вьюхолдере */
    private var viewHolderNestedScrollKey = ""

    init {
        competitionsAdapter.onItemClick = onItemClick
        binding.recyclerView.apply {
            adapter = competitionsAdapter
        }
        scrollStateHolder?.setupRecyclerView(binding.recyclerView, this)
    }

    fun bind(block: MainSection.CompetitionsBlock) = with(binding) {
        viewHolderNestedScrollKey = block.title
        textViewBlockTitle.text = block.title
        recyclerView.layoutManager = when (block.viewType) {
            CompetitionViewType.ROW -> LinearLayoutManager(root.context, LinearLayoutManager.HORIZONTAL, false)
            CompetitionViewType.CARD -> GridLayoutManager(root.context, GRID_SPAN_COUNT)
        }
        snapHelper.attachToRecyclerView(null)
        recyclerView.apply {
            if (itemDecorationCount > 0) {
                removeItemDecorationAt(0)
            }
            when (block.viewType) {
                CompetitionViewType.ROW -> addLinearSpaceItemDecoration(R.dimen.padding_8)
                CompetitionViewType.CARD -> addDefaultGridSpaceItemDecoration(R.dimen.padding_16)
            }
        }
        if (block.viewType == CompetitionViewType.ROW) {
            snapHelper.attachToRecyclerView(recyclerView)
        }
        competitionsAdapter.viewType = block.viewType
        competitionsAdapter.submitList(block.items)
        scrollStateHolder?.restoreScrollState(recyclerView, this@MainSectionCompetitionsViewHolder)
    }

    override fun getScrollStateKey(): String = viewHolderNestedScrollKey

    override fun onRecycled() {
        scrollStateHolder?.apply {
            saveScrollState(binding.recyclerView, this@MainSectionCompetitionsViewHolder)
        }
    }
}
