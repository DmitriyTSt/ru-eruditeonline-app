package ru.eruditeonline.app.presentation.composeui.result.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import ru.eruditeonline.app.presentation.composeui.base.ObserveDestinations
import ru.eruditeonline.app.presentation.composeui.paging.PagingStateFlipperView
import ru.eruditeonline.app.presentation.composeui.paging.applyFooterState
import ru.eruditeonline.app.presentation.composeui.views.NavigationIcon
import ru.eruditeonline.app.presentation.ui.result.common.CommonResultListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonResultListScreen(navController: NavController, viewModel: CommonResultListViewModel) {
    ObserveDestinations(navController, viewModel)
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val resultsPagingItems = viewModel.resultsLiveData.asFlow().collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        viewModel.callOperations {
            viewModel.load()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.common_result_list_title))
                },
                navigationIcon = {
                    NavigationIcon(navController)
                },
                scrollBehavior = scrollBehavior,
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        contentWindowInsets = WindowInsets.statusBars,
    ) { innerPaddings ->
        PagingStateFlipperView(
            items = resultsPagingItems,
            onRetryClick = {
                viewModel.load()
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings),
        ) {
            LazyColumn(
                contentPadding = PaddingValues(vertical = 8.dp),
            ) {
                items(
                    count = resultsPagingItems.itemCount,
                ) { index ->
                    val item = resultsPagingItems[index]
                    if (item != null) {
                        TopResultView(topResult = item, onClick = {})
                        if (index < resultsPagingItems.itemCount - 1) {
                            Divider()
                        }
                    }
                }
                applyFooterState(resultsPagingItems)
            }
        }
    }
}