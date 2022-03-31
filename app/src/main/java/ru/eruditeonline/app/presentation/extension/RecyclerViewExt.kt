package ru.eruditeonline.app.presentation.extension

import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.ui.views.GridSpaceItemDecoration
import ru.eruditeonline.app.presentation.ui.views.LinearSpaceItemDecoration

fun RecyclerView.addLinearSpaceItemDecoration(
    @DimenRes spacing: Int = R.dimen.padding_4,
    showFirstVerticalDivider: Boolean = false,
    showLastVerticalDivider: Boolean = false,
    showFirstHorizontalDivider: Boolean = false,
    showLastHorizontalDivider: Boolean = false,
    conditionProvider: LinearSpaceItemDecoration.ConditionProvider? = null,
) {
    this.addItemDecoration(
        LinearSpaceItemDecoration(
            context.resources.getDimensionPixelSize(spacing),
            showFirstVerticalDivider,
            showLastVerticalDivider,
            showFirstHorizontalDivider,
            showLastHorizontalDivider,
            conditionProvider,
        )
    )
}

fun RecyclerView.addDefaultGridSpaceItemDecoration(
    spanCount: Int,
    @DimenRes spacing: Int = R.dimen.padding_8,
    includeEdge: Boolean = false,
    excludeTopEdge: Boolean = true
) {
    val itemDecoration = GridSpaceItemDecoration(
        spanCount,
        context.resources.getDimensionPixelSize(spacing),
        includeEdge,
        excludeTopEdge
    )
    this.addItemDecoration(itemDecoration)
}

fun RecyclerView.disableChangeAnimations() {
    (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
}