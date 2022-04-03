package ru.eruditeonline.app.presentation.paging

import androidx.recyclerview.widget.DiffUtil
import ru.eruditeonline.app.data.model.Similarable
import javax.inject.Inject

/**
 * Factory that created DiffUtil.ItemCallback
 * It used in RecyclerView Adapters through Dagger's injections
 */
class DiffUtilItemCallbackFactory @Inject constructor() {

    fun <T : Similarable<T>> create(): DiffUtil.ItemCallback<T> {
        return object : DiffUtil.ItemCallback<T>() {
            override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.areItemsTheSame(newItem)
            override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem.areContentsTheSame(newItem)
        }
    }
}
