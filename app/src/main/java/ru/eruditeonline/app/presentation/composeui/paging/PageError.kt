package ru.eruditeonline.app.presentation.composeui.paging

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.parseError
import ru.eruditeonline.app.presentation.composeui.theme.AppTypography

@Composable
fun PageError(throwable: Throwable, modifier: Modifier = Modifier, onRetryClick: () -> Unit) {
    val error = throwable.parseError()
    Column(modifier = modifier.padding(vertical = 16.dp)) {
        Text(
            text = error.message,
            style = AppTypography.bodySmall,
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
                style = AppTypography.bodySmall,
            )
        }
    }
}