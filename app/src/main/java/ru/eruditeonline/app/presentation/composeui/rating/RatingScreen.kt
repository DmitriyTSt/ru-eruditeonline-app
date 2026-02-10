package ru.eruditeonline.app.presentation.composeui.rating

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.presentation.composeui.views.StateFlipperView
import ru.eruditeonline.app.presentation.ui.rating.tab.RatingTabItemMode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RatingScreen() {
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
    var isDatePickerVisible by rememberSaveable { mutableStateOf(false) }
    val selectedMode = RatingTabItemMode.entries[selectedTabIndex]

    val dayViewModel = ratingTabViewModel(key = "rating-day")
    val monthViewModel = ratingTabViewModel(key = "rating-month")
    val yearViewModel = ratingTabViewModel(key = "rating-year")

    LaunchedEffect(dayViewModel) {
        dayViewModel.callOperations {
            dayViewModel.initLoad(RatingTabItemMode.DAY)
        }
    }
    LaunchedEffect(monthViewModel) {
        monthViewModel.callOperations {
            monthViewModel.initLoad(RatingTabItemMode.MONTH)
        }
    }
    LaunchedEffect(yearViewModel) {
        yearViewModel.callOperations {
            yearViewModel.initLoad(RatingTabItemMode.YEAR)
        }
    }

    val currentViewModel = when (selectedMode) {
        RatingTabItemMode.DAY -> dayViewModel
        RatingTabItemMode.MONTH -> monthViewModel
        RatingTabItemMode.YEAR -> yearViewModel
    }

    val ratingState by currentViewModel.ratingLiveData.observeAsState(LoadableState.Loading())
    val selectedDate by currentViewModel.selectedDateLiveData.observeAsState(defaultDateForMode(selectedMode))

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(text = stringResource(R.string.rating_title))
                    }
                )
                PrimaryTabRow(selectedTabIndex = selectedTabIndex) {
                    RatingTabItemMode.entries.forEachIndexed { index, mode ->
                        Tab(
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            text = {
                                Text(text = stringResource(id = mode.titleRes))
                            }
                        )
                    }
                }
            }
        },
        contentWindowInsets = WindowInsets.systemBars,
    ) { innerPaddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = innerPaddings.calculateTopPadding(),
                    start = 16.dp,
                    end = 16.dp,
                )
        ) {
            RatingDateSelectorField(
                mode = selectedMode,
                selectedDate = selectedDate,
                onClick = { isDatePickerVisible = true },
                modifier = Modifier.fillMaxWidth()
            )

            StateFlipperView(
                state = ratingState,
                onRetryClick = {
                    currentViewModel.retry(selectedMode)
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 12.dp),
            ) { rating ->
                if (rating.isEmpty()) {
                    EmptyRatingView(modifier = Modifier.fillMaxSize())
                } else {
                    RatingList(
                        rating = rating,
                        innerPaddings = innerPaddings,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
        }
    }

    if (isDatePickerVisible) {
        RatingDateDialog(
            mode = selectedMode,
            selectedDate = selectedDate,
            onDateSelected = { pickedDate ->
                currentViewModel.selectDate(selectedMode, pickedDate)
            },
            onDismissRequest = { isDatePickerVisible = false },
        )
    }
}