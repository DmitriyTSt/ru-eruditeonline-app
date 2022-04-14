package ru.eruditeonline.app.presentation.ui.rating.tab.period

import android.view.ViewGroup
import ru.eruditeonline.app.presentation.ui.base.BaseAdapter
import javax.inject.Inject

class DatePeriodsAdapter @Inject constructor() : BaseAdapter<DatePeriod, DatePeriodViewHolder>() {

    lateinit var onItemClick: (DatePeriod) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatePeriodViewHolder {
        return DatePeriodViewHolder(parent, onItemClick)
    }

    override fun onBindViewHolder(holder: DatePeriodViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
