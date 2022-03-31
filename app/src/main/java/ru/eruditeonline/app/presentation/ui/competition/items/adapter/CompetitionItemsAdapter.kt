package ru.eruditeonline.app.presentation.ui.competition.items.adapter

import android.view.ViewGroup
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.competition.CompetitionItemShort
import ru.eruditeonline.app.presentation.paging.DiffUtilItemCallbackFactory
import ru.eruditeonline.app.presentation.ui.competition.items.CompetitionItemsViewType
import ru.eruditeonline.app.presentation.ui.views.RecyclerViewAdapterWithCustomSpanSize
import javax.inject.Inject

class CompetitionItemsAdapter @Inject constructor(
    diffUtilItemCallbackFactory: DiffUtilItemCallbackFactory,
) : PagingDataAdapter<CompetitionItemShort, BaseCompetitionViewHolder>(diffUtilItemCallbackFactory.create()),
    RecyclerViewAdapterWithCustomSpanSize {

    lateinit var onItemClick: (CompetitionItemShort) -> Unit

    var spanCount = 2
    var viewType = CompetitionItemsViewType.CARD

    /** Нужен тут для того, чтобы декоратору было откуда брать информацию о количестве столбцов */
    private var recyclerView: RecyclerView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseCompetitionViewHolder {
        return when (viewType) {
            R.layout.item_competition_card -> CompetitionCardViewHolder(parent, onItemClick)
            R.layout.item_competition_row -> CompetitionRowViewHolder(parent, onItemClick)
            else -> throw IllegalStateException("Unsupported view type")
        }
    }

    override fun onBindViewHolder(holder: BaseCompetitionViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun getItemViewType(position: Int): Int {
        return when (viewType) {
            CompetitionItemsViewType.CARD -> R.layout.item_competition_card
            CompetitionItemsViewType.ROW -> R.layout.item_competition_row
        }
    }

    override fun getSpanSize(position: Int): Int {
        val maxColumns = 2
        val defaultColumns = maxColumns / spanCount
        recyclerView?.let { rv ->
            val concatAdapter = rv.adapter as ConcatAdapter
            val itemsAmount = concatAdapter.adapters.sumBy {
                if (it !is LoadStateAdapter) it.itemCount else 0
            }
            return if (position >= itemsAmount) maxColumns else defaultColumns
        } ?: run {
            return defaultColumns
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }
}