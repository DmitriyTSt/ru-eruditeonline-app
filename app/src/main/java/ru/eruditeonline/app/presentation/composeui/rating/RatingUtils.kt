package ru.eruditeonline.app.presentation.composeui.rating

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.eruditeonline.app.presentation.composeui.base.LocalViewModelFactory
import ru.eruditeonline.app.presentation.ui.rating.tab.RatingTabItemMode
import ru.eruditeonline.app.presentation.ui.rating.tab.RatingTabItemViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
internal fun ratingTabViewModel(key: String): RatingTabItemViewModel {
    val viewModelFactory: ViewModelProvider.Factory = LocalViewModelFactory.current
    return viewModel(
        key = key,
        factory = viewModelFactory,
    )
}

internal fun defaultDateForMode(mode: RatingTabItemMode): LocalDate {
    val now = LocalDate.now()
    return if (mode == RatingTabItemMode.YEAR) now.minusYears(1) else now
}

@Composable
internal fun formatDateForMode(mode: RatingTabItemMode, date: LocalDate): String {
    // Recompute with current locale changes (e.g. language switch from settings).
    LocalConfiguration.current
    return when (mode) {
        RatingTabItemMode.DAY -> DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.getDefault()).format(date)
        RatingTabItemMode.MONTH -> DateTimeFormatter.ofPattern("LLLL yyyy", Locale.getDefault()).format(date)
        RatingTabItemMode.YEAR -> "${date.year} - ${date.plusYears(1).year}"
    }
}
