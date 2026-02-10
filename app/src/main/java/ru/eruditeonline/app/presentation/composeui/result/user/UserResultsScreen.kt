package ru.eruditeonline.app.presentation.composeui.result.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asFlow
import androidx.paging.compose.collectAsLazyPagingItems
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.test.TestUserResultRow
import ru.eruditeonline.app.presentation.composeui.base.ObserveDestinations
import ru.eruditeonline.app.presentation.composeui.base.appViewModel
import ru.eruditeonline.app.presentation.composeui.paging.PagingStateFlipperView
import ru.eruditeonline.app.presentation.composeui.paging.applyFooterState
import ru.eruditeonline.app.presentation.composeui.views.NavigationIcon
import ru.eruditeonline.app.presentation.ui.result.user.UserResultListViewModel
import ru.eruditeonline.app.presentation.ui.result.user.UserResultParams
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserResultsScreen(initialEmail: String? = null, viewModel: UserResultListViewModel = appViewModel()) {
    viewModel.ObserveDestinations()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val isEmailMode = !initialEmail.isNullOrBlank()
    var isSearchVisible by rememberSaveable(initialEmail) { mutableStateOf(isEmailMode) }
    var searchQuery by rememberSaveable(initialEmail) { mutableStateOf(initialEmail.orEmpty()) }

    val resultsPagingItems = viewModel.resultsLiveData.asFlow().collectAsLazyPagingItems()

    LaunchedEffect(initialEmail) {
        viewModel.callOperations {
            if (initialEmail.isNullOrBlank()) {
                viewModel.init(UserResultParams.All)
            } else {
                viewModel.init(UserResultParams.Email(initialEmail))
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (isSearchVisible) {
                        TextField(
                            value = searchQuery,
                            onValueChange = { query ->
                                searchQuery = query
                                if (!isEmailMode) {
                                    viewModel.search(query)
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text(text = stringResource(id = R.string.search_result_hint)) },
                            singleLine = true,
                            readOnly = isEmailMode,
                            keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                                imeAction = ImeAction.Search,
                            ),
                        )
                    } else {
                        Text(text = stringResource(id = R.string.user_result_list_title))
                    }
                },
                navigationIcon = {
                    NavigationIcon(viewModel::navigateBack)
                },
                actions = {
                    if (isEmailMode) {
                        IconButton(onClick = viewModel::navigateBack) {
                            Icon(
                                painter = painterResource(R.drawable.ic_close),
                                contentDescription = null,
                            )
                        }
                    } else {
                        if (isSearchVisible) {
                            IconButton(onClick = {
                                searchQuery = ""
                                isSearchVisible = false
                                viewModel.search("")
                            }) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_close),
                                    contentDescription = null,
                                )
                            }
                        } else {
                            IconButton(onClick = { isSearchVisible = true }) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_search),
                                    contentDescription = null,
                                )
                            }
                        }
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        contentWindowInsets = WindowInsets.systemBars,
    ) { innerPadding ->
        PagingStateFlipperView(
            items = resultsPagingItems,
            onRetryClick = {
                if (initialEmail.isNullOrBlank()) {
                    viewModel.init(UserResultParams.All)
                } else {
                    viewModel.init(UserResultParams.Email(initialEmail))
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            if (resultsPagingItems.itemCount == 0) {
                UserResultsEmptyState()
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(count = resultsPagingItems.itemCount) { index ->
                        resultsPagingItems[index]?.let { result ->
                            UserResultCard(result = result)
                        }
                    }
                    applyFooterState(resultsPagingItems)
                }
            }
        }
    }
}

@Composable
private fun UserResultsEmptyState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(id = R.string.user_result_empty_title),
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            text = stringResource(id = R.string.user_result_empty_comment),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 4.dp),
        )
    }
}

@Composable
private fun UserResultCard(result: TestUserResultRow) {
    val dateText = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.getDefault()).format(result.date)
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = {},
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = dateText,
                    style = MaterialTheme.typography.bodySmall,
                )
                Text(
                    text = "${result.testId} № ${result.id}",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            Text(
                text = result.username,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 4.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = stringResource(id = R.string.user_result_place_label),
                    style = MaterialTheme.typography.titleSmall,
                )
                Text(
                    text = result.place,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = stringResource(id = R.string.user_result_score_label),
                    style = MaterialTheme.typography.titleSmall,
                )
                Text(
                    text = stringResource(
                        id = R.string.score_template,
                        result.score.current,
                        result.score.max,
                    ),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
            Text(
                text = result.competitionTitle,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 4.dp),
            )
        }
    }
}


