package ru.eruditeonline.app.presentation.composeui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.composeui.theme.AppTypography
import ru.eruditeonline.app.presentation.composeui.theme.EruditeTheme
import ru.eruditeonline.app.presentation.composeui.theme.EruditeThemeModel
import ru.eruditeonline.app.presentation.composeui.views.NavigationIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    viewModel: ComposeSettingsViewModel,
) {
    val currentTheme by viewModel.currentThemeLiveData.observeAsState(EruditeThemeModel.DEFAULT)
    SettingsScreenContent(
        currentTheme = currentTheme,
        onBackClick = onBackClick,
        selectTheme = viewModel::changeTheme,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsScreenContent(
    currentTheme: EruditeThemeModel,
    onBackClick: () -> Unit,
    selectTheme: (EruditeThemeModel) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.settings_title))
                },
                navigationIcon = {
                    NavigationIcon(onBackClick)
                }
            )
        }
    ) { innerPaddings ->
        Column(Modifier.padding(innerPaddings)) {
            Text(
                text = "Тема приложения",
                style = AppTypography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Column(
                Modifier
                    .selectableGroup()
            ) {
                EruditeThemeModel.entries.forEach { eruditeTheme ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selectTheme(eruditeTheme) }
                    ) {
                        RadioButton(
                            selected = currentTheme == eruditeTheme,
                            onClick = { selectTheme(eruditeTheme) },
                        )
                        Text(text = eruditeTheme.title)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    EruditeTheme {
        SettingsScreenContent(
            currentTheme = EruditeThemeModel.STANDARD_LIGHT,
            onBackClick = {},
            selectTheme = {},
        )
    }
}