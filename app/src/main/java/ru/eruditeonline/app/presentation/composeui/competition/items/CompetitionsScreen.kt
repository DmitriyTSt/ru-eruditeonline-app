package ru.eruditeonline.app.presentation.composeui.competition.items

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asFlow
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.competition.CompetitionFilters
import ru.eruditeonline.app.presentation.composeui.base.ObserveDestinations
import ru.eruditeonline.app.presentation.composeui.base.SetResultListener
import ru.eruditeonline.app.presentation.composeui.paging.PagingStateFlipperView
import ru.eruditeonline.app.presentation.composeui.paging.applyFooterState
import ru.eruditeonline.app.presentation.composeui.paging.isSuccess
import ru.eruditeonline.app.presentation.composeui.views.CompetitionItemBigGridView
import ru.eruditeonline.app.presentation.composeui.views.CompetitionItemSmallRowView
import ru.eruditeonline.app.presentation.ui.competition.filter.model.FilterRequest
import ru.eruditeonline.app.presentation.ui.competition.items.CompetitionItemsViewModel
import ru.eruditeonline.app.presentation.ui.competition.items.CompetitionItemsViewType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompetitionsScreen(navController: NavController, viewModel: CompetitionItemsViewModel) {
    ObserveDestinations(navController, viewModel)
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val competitionPagingItems = viewModel.pagingDataLiveData.asFlow().collectAsLazyPagingItems()
    val listViewType by viewModel.listViewTypeLiveData.observeAsState(CompetitionItemsViewType.CARD)
    val filters by viewModel.filtersLiveData.observeAsState(CompetitionFilters(emptyList(), emptyList()))

    navController.SetResultListener<FilterRequest> { filterRequest ->
        viewModel.loadCompetitions(
            ageIds = filterRequest.ageIds,
            subjectIds = filterRequest.subjectIds,
        )
    }

    LaunchedEffect(Unit) {
        viewModel.callOperations {
            viewModel.loadCompetitions()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.competition_items_title))
                },
                scrollBehavior = scrollBehavior,
                actions = {
                    if (competitionPagingItems.isSuccess) {
                        IconButton(onClick = { viewModel.changeListViewType() }) {
                            val viewTypeIconRes = when (listViewType) {
                                CompetitionItemsViewType.CARD -> R.drawable.ic_view_type_card
                                CompetitionItemsViewType.ROW -> R.drawable.ic_view_type_row
                            }
                            Icon(
                                painter = painterResource(id = viewTypeIconRes),
                                contentDescription = null,
                            )
                        }
                        IconButton(onClick = {
                            viewModel.openFilter(filters)
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_filter),
                                contentDescription = null,
                            )
                        }
                    }
                }
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        contentWindowInsets = WindowInsets.statusBars,
    ) { innerPaddings ->
        PagingStateFlipperView(
            items = competitionPagingItems,
            onRetryClick = {
                // TODO retry actual
                viewModel.loadCompetitions()
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings),
        ) {
            LazyColumn(
                contentPadding = PaddingValues(12.dp),
            ) {
                when (listViewType) {
                    CompetitionItemsViewType.CARD -> {
                        items(
                            count = (competitionPagingItems.itemCount + 1) / 2,
                        ) { index ->
                            val competitionItemLeft = competitionPagingItems[index * 2]
                            val rightIndex = index * 2 + 1
                            val competitionItemRight = if (rightIndex < competitionPagingItems.itemCount) {
                                competitionPagingItems[index * 2 + 1]
                            } else {
                                null
                            }
                            Row(
                                modifier = Modifier
                                    // весь хак с двумя айтемами в строке вместо грида из-за этого
                                    .height(IntrinsicSize.Min)
                                    .fillMaxWidth()
                            ) {
                                if (competitionItemLeft != null) {
                                    CompetitionItemBigGridView(
                                        competitionItem = competitionItemLeft,
                                        modifier = Modifier
                                            .padding(4.dp)
                                            .weight(1f)
                                            .fillMaxHeight(),
                                        onClick = { item ->
                                            viewModel.openCompetition(item)
                                        }
                                    )
                                }
                                if (competitionItemRight != null) {
                                    CompetitionItemBigGridView(
                                        competitionItem = competitionItemRight,
                                        modifier = Modifier
                                            .padding(4.dp)
                                            .weight(1f)
                                            .fillMaxHeight(),
                                        onClick = { item ->
                                            viewModel.openCompetition(item)
                                        }
                                    )
                                } else {
                                    Spacer(
                                        modifier = Modifier
                                            .padding(4.dp)
                                            .weight(1f)
                                            .fillMaxHeight(),
                                    )
                                }
                            }
                        }
                    }
                    CompetitionItemsViewType.ROW -> {
                        items(
                            count = competitionPagingItems.itemCount,
                        ) { index ->
                            val competitionItem = competitionPagingItems[index]
                            if (competitionItem != null) {
                                CompetitionItemSmallRowView(
                                    competitionItem = competitionItem,
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .fillMaxWidth(),
                                    onClick = { item ->
                                        viewModel.openCompetition(item)
                                    }
                                )
                            }
                        }
                    }
                }

                applyFooterState(competitionPagingItems)
            }
        }
    }
}
