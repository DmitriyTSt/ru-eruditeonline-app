package ru.eruditeonline.app.presentation.composeui.rating

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.managers.DateFormatter
import ru.eruditeonline.app.presentation.ui.rating.tab.RatingTabItemMode
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneOffset

@Composable
internal fun RatingDateSelectorField(
    mode: RatingTabItemMode,
    selectedDate: LocalDate,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        OutlinedTextField(
            value = formatDateForMode(mode, selectedDate),
            onValueChange = {},
            readOnly = true,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = stringResource(
                        id = when (mode) {
                            RatingTabItemMode.DAY -> R.string.rating_day_hint
                            RatingTabItemMode.MONTH -> R.string.rating_month_hint
                            RatingTabItemMode.YEAR -> R.string.rating_year_hint
                        }
                    )
                )
            },
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable(onClick = onClick)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RatingDateDialog(
    mode: RatingTabItemMode,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    onDismissRequest: () -> Unit,
) {
    when (mode) {
        RatingTabItemMode.DAY -> {
            val initialMillis = remember(selectedDate) {
                selectedDate
                    .atStartOfDay(ZoneOffset.UTC)
                    .toInstant()
                    .toEpochMilli()
            }
            val datePickerState = rememberDatePickerState(initialSelectedDateMillis = initialMillis)
            DatePickerDialog(
                onDismissRequest = onDismissRequest,
                confirmButton = {
                    TextButton(
                        onClick = {
                            val pickedDate = datePickerState.selectedDateMillis
                                ?.let { millis ->
                                    Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate()
                                } ?: selectedDate
                            onDateSelected(pickedDate)
                            onDismissRequest()
                        }
                    ) {
                        Text(text = stringResource(android.R.string.ok))
                    }
                },
                dismissButton = {
                    TextButton(onClick = onDismissRequest) {
                        Text(text = stringResource(android.R.string.cancel))
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
        RatingTabItemMode.MONTH -> {
            val dateFormatter = remember { DateFormatter() }
            val periods = remember {
                (0..11)
                    .map { LocalDate.now().minusMonths(it.toLong()) }
                    .map { date -> PeriodOption(date, dateFormatter.formatTextMonthMonth(date)) }
            }
            RatingPeriodsDialog(
                title = stringResource(R.string.rating_month_hint),
                periods = periods,
                onPeriodSelected = {
                    onDateSelected(it.date)
                    onDismissRequest()
                },
                onDismissRequest = onDismissRequest,
            )
        }
        RatingTabItemMode.YEAR -> {
            val dateFormatter = remember { DateFormatter() }
            val periods = remember {
                (2020 until LocalDate.now().year)
                    .map { year -> LocalDate.of(year, 1, 1) }
                    .map { date -> PeriodOption(date, dateFormatter.formatStudyYear(date)) }
            }
            RatingPeriodsDialog(
                title = stringResource(R.string.rating_year_hint),
                periods = periods,
                onPeriodSelected = {
                    onDateSelected(it.date)
                    onDismissRequest()
                },
                onDismissRequest = onDismissRequest,
            )
        }
    }
}

@Composable
private fun RatingPeriodsDialog(
    title: String,
    periods: List<PeriodOption>,
    onPeriodSelected: (PeriodOption) -> Unit,
    onDismissRequest: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(text = title) },
        text = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 360.dp),
            ) {
                items(periods) { period ->
                    Text(
                        text = period.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onPeriodSelected(period) }
                            .padding(vertical = 12.dp),
                    )
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(text = stringResource(android.R.string.cancel))
            }
        },
    )
}

private data class PeriodOption(
    val date: LocalDate,
    val title: String,
)
