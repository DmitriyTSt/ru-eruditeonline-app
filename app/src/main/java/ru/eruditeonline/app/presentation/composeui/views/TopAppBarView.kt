package ru.eruditeonline.app.presentation.composeui.views

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.eruditeonline.app.presentation.composeui.theme.AppTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarView(title: String, modifier: Modifier = Modifier, scrollBehavior: TopAppBarScrollBehavior? = null) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = AppTypography.titleLarge,
            )
        },
        modifier = modifier,
        scrollBehavior = scrollBehavior
    )
}