package ru.eruditeonline.app.presentation.ui.result.common

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import ru.eruditeonline.app.data.model.test.TestCommonResultRow
import ru.eruditeonline.app.presentation.paging.DiffUtilItemCallbackFactory
import javax.inject.Inject

class CommonResultsAdapter @Inject constructor(
    diffUtilItemCallbackFactory: DiffUtilItemCallbackFactory
) : PagingDataAdapter<TestCommonResultRow, CommonResultViewHolder>(diffUtilItemCallbackFactory.create()) {

    lateinit var onItemClick: (TestCommonResultRow) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonResultViewHolder {
        return CommonResultViewHolder(parent, onItemClick)
    }

    override fun onBindViewHolder(holder: CommonResultViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}