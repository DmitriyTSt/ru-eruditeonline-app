package ru.eruditeonline.app.presentation.composeui.result.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.composeui.base.LocalBackStack
import ru.eruditeonline.app.presentation.composeui.result.user.UserResults
import ru.eruditeonline.app.presentation.composeui.views.NavigationIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResultsScreen() {
    val backStack = LocalBackStack.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val searchFocusRequester = remember { FocusRequester() }

    var query by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(Unit) {
        searchFocusRequester.requestFocus()
        keyboardController?.show()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TextField(
                        value = query,
                        onValueChange = { query = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(searchFocusRequester),
                        singleLine = true,
                        placeholder = { Text(text = stringResource(R.string.search_result_by_email_hint)) },
                        keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                            imeAction = ImeAction.Search,
                            keyboardType = KeyboardType.Email,
                        ),
                        keyboardActions = androidx.compose.foundation.text.KeyboardActions(
                            onSearch = {
                                val email = query.trim()
                                if (email.isNotEmpty()) {
                                    keyboardController?.hide()
                                    backStack.add(UserResults(email = email))
                                }
                            },
                        ),
                    )
                },
                navigationIcon = {
                    NavigationIcon {
                        backStack.removeLastOrNull()
                    }
                },
                actions = {
                    if (query.isNotEmpty()) {
                        IconButton(onClick = { query = "" }) {
                            Icon(
                                painter = painterResource(R.drawable.ic_close),
                                contentDescription = null,
                            )
                        }
                    }
                },
            )
        },
        contentWindowInsets = WindowInsets.ime,
    ) { innerPadding ->
        SearchHelpText(innerPadding = innerPadding)
    }
}

@Composable
private fun SearchHelpText(innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Text(
            text = stringResource(id = R.string.search_result_by_email_help_text),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
