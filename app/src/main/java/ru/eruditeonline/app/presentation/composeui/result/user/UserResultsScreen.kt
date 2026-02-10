package ru.eruditeonline.app.presentation.composeui.result.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asFlow
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.collectLatest
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.composeui.base.BottomNavigationSpaceWithInset
import ru.eruditeonline.app.presentation.composeui.base.ObserveDestinations
import ru.eruditeonline.app.presentation.composeui.base.appViewModel
import ru.eruditeonline.app.presentation.composeui.paging.PagingStateFlipperView
import ru.eruditeonline.app.presentation.composeui.paging.applyFooterState
import ru.eruditeonline.app.presentation.composeui.views.NavigationIcon
import ru.eruditeonline.app.presentation.composeui.views.SearchInputField
import ru.eruditeonline.app.presentation.ui.result.user.UserResultListViewModel
import ru.eruditeonline.app.presentation.ui.result.user.UserResultParams

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserResultsScreen(initialEmail: String? = null, viewModel: UserResultListViewModel = appViewModel()) {
    viewModel.ObserveDestinations()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val keyboardController = LocalSoftwareKeyboardController.current
    val searchFocusRequester = remember { FocusRequester() }

    val isEmailMode = !initialEmail.isNullOrBlank()
    var isSearchVisible by rememberSaveable(initialEmail) { mutableStateOf(isEmailMode) }
    val searchState = rememberTextFieldState(initialText = initialEmail.orEmpty())

    val resultsPagingItems = viewModel.resultsLiveData.asFlow().collectAsLazyPagingItems()

    LaunchedEffect(initialEmail) {
        searchState.setTextAndPlaceCursorAtEnd(initialEmail.orEmpty())
        viewModel.callOperations {
            if (initialEmail.isNullOrBlank()) {
                viewModel.init(UserResultParams.All)
            } else {
                viewModel.init(UserResultParams.Email(initialEmail))
            }
        }
    }

    LaunchedEffect(searchState, isEmailMode) {
        if (!isEmailMode) {
            snapshotFlow { searchState.text.toString() }
                .collectLatest(viewModel::search)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (isSearchVisible) {
                        SearchInputField(
                            state = searchState,
                            placeholderText = stringResource(id = R.string.search_result_hint),
                            modifier = Modifier.focusRequester(searchFocusRequester),
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
                                searchState.setTextAndPlaceCursorAtEnd("")
                                isSearchVisible = false
                            }) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_close),
                                    contentDescription = null,
                                )
                            }
                        } else {
                            IconButton(onClick = {
                                isSearchVisible = true
                                searchFocusRequester.requestFocus()
                                keyboardController?.show()
                            }) {
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
                .padding(top = innerPadding.calculateTopPadding())
                .imePadding(),
        ) {
            if (resultsPagingItems.itemCount == 0) {
                UserResultsEmptyState()
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(0.dp),
                ) {
                    items(count = resultsPagingItems.itemCount) { index ->
                        resultsPagingItems[index]?.let { result ->
                            Column {
                                UserResultCard(
                                    result = result,
                                )
                                if (index < resultsPagingItems.itemCount - 1) {
                                    HorizontalDivider(
                                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 8.dp),
                                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.65f),
                                    )
                                }
                            }
                        }
                    }
                    applyFooterState(resultsPagingItems)
                    item {
                        BottomNavigationSpaceWithInset(innerPadding, 8.dp)
                    }
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

