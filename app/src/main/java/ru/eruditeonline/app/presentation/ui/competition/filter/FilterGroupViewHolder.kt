package ru.eruditeonline.app.presentation.ui.competition.filter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.ItemFilterGroupBinding
import ru.eruditeonline.app.presentation.paging.DiffUtilItemCallbackFactory
import ru.eruditeonline.app.presentation.ui.competition.filter.model.FilterGroup
import ru.eruditeonline.ui.presentation.ext.inflate
import ru.eruditeonline.ui.presentation.views.FlexboxSpaceItemDecoration

class FilterGroupViewHolder(
    parent: ViewGroup,
    diffUtilItemCallbackFactory: DiffUtilItemCallbackFactory,
) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_filter_group)) {
    private val binding by viewBinding(ItemFilterGroupBinding::bind)

    private val filtersAdapter = FiltersAdapter(diffUtilItemCallbackFactory)

    init {
        binding.root.apply {
            adapter = filtersAdapter
            layoutManager = FlexboxLayoutManager(context).apply {
                flexDirection = FlexDirection.ROW
                flexWrap = FlexWrap.WRAP
            }
            val space = resources.getDimensionPixelSize(R.dimen.padding_8)
            addItemDecoration(
                FlexboxSpaceItemDecoration(
                    horizontalSpace = space,
                    verticalSpace = space,
                )
            )
        }
    }

    fun bind(group: FilterGroup) = with(binding.root) {
        filtersAdapter.submitList(group.filters)
    }
}
