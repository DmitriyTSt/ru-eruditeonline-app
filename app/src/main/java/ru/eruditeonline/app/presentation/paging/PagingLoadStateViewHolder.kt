package ru.eruditeonline.app.presentation.paging

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.ItemPagingStateBinding
import ru.eruditeonline.ui.presentation.ext.inflate

/**
 * Отображает ошибку и прогресс в ячейке при пагинации
 */
class PagingLoadStateViewHolder(
    parent: ViewGroup,
    retry: () -> Unit,
) : RecyclerView.ViewHolder(
    parent.inflate(R.layout.item_paging_state)
) {

    private val binding by viewBinding(ItemPagingStateBinding::bind)

    init {
        binding.buttonPagingRetry.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) = with(binding) {
        progressBarPagingLoading.isVisible = loadState is LoadState.Loading
        buttonPagingRetry.isVisible = loadState !is LoadState.Loading
        textViewPagingError.isVisible = loadState !is LoadState.Loading
    }
}
