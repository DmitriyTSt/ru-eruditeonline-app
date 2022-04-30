package ru.eruditeonline.app.presentation.ui.dashboard.mainsection

import android.view.ViewGroup
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.main.MainSection
import ru.eruditeonline.app.data.model.main.Tagline
import ru.eruditeonline.app.databinding.ItemMainSectionTaglinesBinding
import ru.eruditeonline.app.presentation.extension.addLinearSpaceItemDecoration
import ru.eruditeonline.app.presentation.extension.inflate
import ru.eruditeonline.app.presentation.ui.dashboard.tagline.TaglinesAdapter
import ru.eruditeonline.app.presentation.ui.views.RecycledViewHolder
import ru.eruditeonline.app.presentation.ui.views.ScrollStateHolder

class MainSectionTaglinesViewHolder(
    parent: ViewGroup,
    private val onTaglineClick: (Tagline) -> Unit,
    private val scrollStateHolder: ScrollStateHolder?,
) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_main_section_taglines)),
    ScrollStateHolder.ScrollStateKeyProvider,
    RecycledViewHolder {

    private val binding by viewBinding(ItemMainSectionTaglinesBinding::bind)

    private val padding8 by lazy { itemView.resources.getDimensionPixelSize(R.dimen.padding_8) }
    private val padding16 by lazy { itemView.resources.getDimensionPixelSize(R.dimen.padding_16) }

    private val taglinesAdapter = TaglinesAdapter().apply {
        onItemClick = onTaglineClick
    }

    init {
        binding.root.apply {
            adapter = taglinesAdapter
            (getChildAt(0) as RecyclerView).apply {
                addLinearSpaceItemDecoration(R.dimen.padding_8)
                updatePadding(top = padding8, bottom = padding8, left = padding16, right = padding16)
                clipToPadding = false
                scrollStateHolder?.setupRecyclerView(this, this@MainSectionTaglinesViewHolder)
            }
        }
    }

    fun bind(block: MainSection.TaglineBlock) {
        taglinesAdapter.submitList(block.taglines)
        scrollStateHolder?.restoreScrollState(
            binding.root.getChildAt(0) as RecyclerView,
            this@MainSectionTaglinesViewHolder
        )
    }

    override fun getScrollStateKey(): String = "taglines"

    override fun onRecycled() {
        scrollStateHolder?.apply {
            saveScrollState(binding.root.getChildAt(0) as RecyclerView, this@MainSectionTaglinesViewHolder)
        }
    }
}
