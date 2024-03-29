package ru.eruditeonline.app.presentation.paging

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

/**
 * Позволяет добалять футер загрузки и ошибки для пагирируемого списка
 * */
class PagingLoadStateAdapter(
    private val retry: () -> Unit,
) : LoadStateAdapter<PagingLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: PagingLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): PagingLoadStateViewHolder {
        return PagingLoadStateViewHolder(parent, retry)
    }
}
