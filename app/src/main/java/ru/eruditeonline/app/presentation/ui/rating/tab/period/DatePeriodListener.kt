package ru.eruditeonline.app.presentation.ui.rating.tab.period

import java.time.LocalDate

interface DatePeriodListener {
    fun selectDate(date: LocalDate)
}
