package ru.eruditeonline.app.presentation.composeui.result.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.composeui.base.LocalBackStack
import ru.eruditeonline.app.presentation.composeui.result.user.UserResults
import ru.eruditeonline.app.presentation.composeui.views.NavigationIcon
import ru.eruditeonline.app.presentation.composeui.views.SearchInputField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResultsScreen() {
    val backStack = LocalBackStack.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val searchFocusRequester = remember { FocusRequester() }
    val queryState = rememberTextFieldState()

    LaunchedEffect(Unit) {
        searchFocusRequester.requestFocus()
        keyboardController?.show()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    SearchInputField(
                        state = queryState,
                        placeholderText = stringResource(R.string.search_result_by_email_hint),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(searchFocusRequester),
                        onClearClick = { queryState.setTextAndPlaceCursorAtEnd("") },
                        keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                            imeAction = ImeAction.Search,
                            keyboardType = KeyboardType.Email,
                        ),
                        onKeyboardActions = KeyboardActionHandler {
                            val email = queryState.text.toString().trim()
                            if (email.isNotEmpty()) {
                                keyboardController?.hide()
                                backStack.add(UserResults(email = email))
                            }
                        },
                    )
                },
                navigationIcon = {
                    NavigationIcon {
                        backStack.removeLastOrNull()
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
