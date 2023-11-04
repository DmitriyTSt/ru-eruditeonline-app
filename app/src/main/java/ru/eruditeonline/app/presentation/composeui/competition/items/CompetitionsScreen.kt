package ru.eruditeonline.app.presentation.composeui.competition.items

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asFlow
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.composeui.model.Screen
import ru.eruditeonline.app.presentation.composeui.paging.PagingStateFlipperView
import ru.eruditeonline.app.presentation.composeui.paging.applyFooterState
import ru.eruditeonline.app.presentation.composeui.views.CompetitionItemBigGridView
import ru.eruditeonline.app.presentation.composeui.views.TopAppBarView
import ru.eruditeonline.app.presentation.ui.competition.items.CompetitionItemsViewModel
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompetitionsScreen(navController: NavController, viewModel: CompetitionItemsViewModel) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val competitionPagingItems = viewModel.pagingDataLiveData.asFlow().collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        viewModel.callOperations {
            viewModel.loadCompetitions()
        }
    }

    Scaffold(
        topBar = {
            TopAppBarView(
                title = stringResource(id = R.string.competition_items_title),
                scrollBehavior = scrollBehavior,
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        contentWindowInsets = WindowInsets.statusBars,
    ) { innerPaddings ->
        val spanCount = 2
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
            LazyVerticalGrid(
                columns = GridCells.Fixed(spanCount),
                contentPadding = PaddingValues(12.dp),
            ) {
                items(competitionPagingItems.itemCount) { index ->
                    val competitionItem = competitionPagingItems[index]
                    if (competitionItem != null) {
                        CompetitionItemBigGridView(
                            competitionItem = competitionItem,
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxHeight(),
                            onClick = {
                                navController.navigate(Screen.Competition.route(Random.nextInt()))
                            }
                        )
                    }
                }

                applyFooterState(spanCount, competitionPagingItems)
            }
        }
    }
}