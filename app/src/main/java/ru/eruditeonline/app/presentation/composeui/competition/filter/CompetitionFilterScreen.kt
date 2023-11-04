package ru.eruditeonline.app.presentation.composeui.competition.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.asFlow
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.competition.CompetitionFilters
import ru.eruditeonline.app.data.model.competition.FilterItem
import ru.eruditeonline.app.presentation.composeui.base.GetScreenArguments
import ru.eruditeonline.app.presentation.composeui.base.setResult
import ru.eruditeonline.app.presentation.composeui.theme.AppTypography
import ru.eruditeonline.app.presentation.composeui.theme.EruditeTheme
import ru.eruditeonline.app.presentation.composeui.views.NavigationIcon
import ru.eruditeonline.app.presentation.ui.competition.filter.CompetitionFilterViewModel
import ru.eruditeonline.app.presentation.ui.competition.filter.model.FilterGroup

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CompetitionFilterScreen(navController: NavController, viewModel: CompetitionFilterViewModel) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    navController.GetScreenArguments<CompetitionFilters> {
        viewModel.initFilters(it)
    }

    LaunchedEffect(Unit) {
        viewModel.applyFilterLiveEvent.asFlow().collectLatest { filterRequest ->
            navController.setResult(filterRequest)
            navController.popBackStack()
        }
    }

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val filterGroups by viewModel.filtersLiveData.observeAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.filter_title),
                        style = AppTypography.titleLarge,
                    )
                },
                navigationIcon = {
                    NavigationIcon(navController)
                },
                scrollBehavior = scrollBehavior,
                actions = {
                    TextButton(onClick = { viewModel.resetFilters() }) {
                        Text(text = stringResource(R.string.reset_filters_label))
                    }
                }
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        contentWindowInsets = WindowInsets.statusBars,
    ) { innerPaddings ->
        Column(Modifier.padding(innerPaddings)) {
            FilterGroupTabs(
                selectedTabIndex = selectedTabIndex,
                onTabClick = {
                    selectedTabIndex = it
                },
                filterGroups = filterGroups,
                modifier = Modifier.zIndex(2f),
            )

            Box(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .weight(1f),
            ) {
                if (filterGroups.isNotEmpty()) {
                    val filters = filterGroups[selectedTabIndex].filters
                    FlowRow(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        filters.forEach { filterItem ->
                            FilterItemView(filterItem, viewModel::onFilterClick)
                        }
                    }
                }
            }

            Divider()
            ElevatedButton(
                onClick = { viewModel.applyFilter(filterGroups) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
            ) {
                Text(text = stringResource(R.string.filter_apply_button_label))
            }
        }
    }
}

@Composable
private fun FilterGroupTabs(
    selectedTabIndex: Int,
    onTabClick: (Int) -> Unit,
    filterGroups: List<FilterGroup>,
    modifier: Modifier = Modifier,
) {
    TabRow(selectedTabIndex = selectedTabIndex, modifier = modifier) {
        filterGroups.forEachIndexed { index, filterGroup ->
            Tab(
                selected = index == selectedTabIndex,
                onClick = { onTabClick(index) },
                text = {
                    Text(text = stringResource(filterGroup.titleRes))
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FilterItemView(filterItem: FilterItem, onClick: (FilterItem) -> Unit) {
    FilterChip(
        selected = filterItem.selected,
        onClick = { onClick(filterItem) },
        label = { Text(text = filterItem.title) },
        modifier = Modifier.padding(horizontal = 4.dp)
    )
}

@Preview
@Composable
private fun FilterItemViewPreview() {
    val filterItemSelected = FilterItem("", "1 класс", true)
    val filterItemUnselected = FilterItem("", "11 класс", false)
    EruditeTheme {
        Surface {
            Column {
                FilterItemView(filterItem = filterItemSelected, onClick = {})
                FilterItemView(filterItem = filterItemUnselected, onClick = {})
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    EruditeTheme {
        CompetitionFilterScreen(NavController(LocalContext.current), CompetitionFilterViewModel())
    }
}
