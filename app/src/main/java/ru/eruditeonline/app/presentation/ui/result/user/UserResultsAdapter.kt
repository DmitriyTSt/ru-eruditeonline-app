package ru.eruditeonline.app.presentation.ui.result.user

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import ru.eruditeonline.app.data.model.test.TestUserResultRow
import ru.eruditeonline.app.presentation.managers.DateFormatter
import ru.eruditeonline.app.presentation.paging.DiffUtilItemCallbackFactory
import javax.inject.Inject

class UserResultsAdapter @Inject constructor(
    diffUtilItemCallbackFactory: DiffUtilItemCallbackFactory,
    private val dateFormatter: DateFormatter,
) : PagingDataAdapter<TestUserResultRow, UserResultViewHolder>(diffUtilItemCallbackFactory.create()) {

    lateinit var onItemClick: (TestUserResultRow) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserResultViewHolder {
        return UserResultViewHolder(parent, dateFormatter, onItemClick)
    }

    override fun onBindViewHolder(holder: UserResultViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}
