package ru.eruditeonline.app.presentation.managers

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

/** Пример: "07.04.2022" */
private const val STANDARD_DATE_TEMPLATE = "dd.MM.yyyy"

/** Пример: "7 апреля 2022" */
private const val TEXT_MONTH_FULL_TEMPLATE = "d MMMM yyyy"

/** Пример: "апрель 2022" */
private const val TEXT_MONTH_MONTH_TEMPLATE = "LLLL yyyy"

/** Пример: "2022" */
private const val YEAR_TEMPLATE = "yyyy"

class DateFormatter @Inject constructor() {
    private val textMonthFullTemplate = DateTimeFormatter.ofPattern(TEXT_MONTH_FULL_TEMPLATE, Locale.getDefault())
    private val standardDateTemplate = DateTimeFormatter.ofPattern(STANDARD_DATE_TEMPLATE, Locale.getDefault())
    private val textMonthMonthTemplate = SimpleDateFormat(TEXT_MONTH_MONTH_TEMPLATE, Locale.getDefault())
    private val yearTemplate = DateTimeFormatter.ofPattern(YEAR_TEMPLATE, Locale.getDefault())

    fun formatStandardDate(date: LocalDate): String {
        return standardDateTemplate.format(date)
    }

    fun formatTextMonthFull(date: LocalDate): String {
        return textMonthFullTemplate.format(date)
    }

    fun formatTextMonthMonth(date: LocalDate): String {
        return textMonthMonthTemplate.format(date.atStartOfDay().toEpochSecond(ZoneOffset.UTC) * 1000)
    }

    fun formatYear(date: LocalDate): String {
        return yearTemplate.format(date)
    }

    fun formatStudyYear(date: LocalDate): String {
        return "${formatYear(date)} - ${formatYear(date.plusYears(1))}"
    }
}
