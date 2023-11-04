package ru.eruditeonline.app.presentation.composeui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.data.model.ParsedError
import ru.eruditeonline.app.presentation.composeui.theme.AppTypography

@Composable
fun <T> StateFlipperView(
    state: LoadableState<T>,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier,
    loadingContent: @Composable BoxScope.() -> Unit = { StateFlipperViewLoadingView() },
    errorContent: @Composable BoxScope.(ParsedError, Throwable) -> Unit = { error, _ ->
        StateFlipperViewErrorView(error, onRetryClick)
    },
    content: @Composable BoxScope.(T) -> Unit,
) {
    Box(
        modifier = modifier
    ) {
        when (state) {
            is LoadableState.Error -> errorContent(state.error, state.throwable)
            is LoadableState.Loading -> loadingContent()
            is LoadableState.Success -> content(state.data)
        }
    }
}

@Composable
fun BoxScope.StateFlipperViewLoadingView() {
    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
}

@Composable
fun BoxScope.StateFlipperViewErrorView(error: ParsedError, onRetryClick: () -> Unit) {
    val titleRes = when (error) {
        is ParsedError.ApiError -> R.string.error_something_wrong_title
        is ParsedError.GeneralError -> R.string.error_something_wrong_title
        is ParsedError.NetworkError -> R.string.error_no_network_title
    }
    val description = when (error) {
        is ParsedError.ApiError -> error.message
        is ParsedError.GeneralError -> stringResource(R.string.error_something_wrong_description)
        is ParsedError.NetworkError -> stringResource(R.string.error_no_network_description)
    }
    Column(modifier = Modifier.align(Alignment.Center)) {
        Text(
            text = stringResource(titleRes),
            style = AppTypography.titleSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp)
        )
        Text(
            text = description,
            style = AppTypography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp)
        )
        Button(
            onClick = { onRetryClick() },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp),
        ) {
            Text(
                text = stringResource(id = R.string.error_retry),
                style = AppTypography.bodyMedium,
            )
        }
    }
}
