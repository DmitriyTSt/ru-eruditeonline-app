package ru.eruditeonline.app.presentation.ui.rating.tab.period

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.ItemDatePeriodBinding
import ru.eruditeonline.app.presentation.extension.inflate

class DatePeriodViewHolder(
    parent: ViewGroup,
    private val onItemClick: (DatePeriod) -> Unit,
) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_date_period)) {
    private val binding by viewBinding(ItemDatePeriodBinding::bind)

    fun bind(period: DatePeriod) = with(binding.root) {
        text = period.formatted
        setOnClickListener {
            onItemClick(period)
        }
    }
}
