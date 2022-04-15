package ru.eruditeonline.app.presentation.ui.rating.tab

import androidx.annotation.StringRes
import ru.eruditeonline.app.R

enum class RatingTabItemMode(@StringRes val titleRes: Int) {
    DAY(R.string.rating_by_day_title),
    MONTH(R.string.rating_by_month_title),
    YEAR(R.string.rating_by_year_title),
}
