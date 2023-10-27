package ru.eruditeonline.app.presentation.composeui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.composeui.theme.AppTypography
import ru.eruditeonline.app.presentation.composeui.theme.EruditeTheme
import ru.eruditeonline.app.presentation.composeui.theme.EruditeThemeModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    currentTheme: EruditeThemeModel,
    selectTheme: (EruditeThemeModel) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Настройки",
                        style = AppTypography.titleLarge,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back_dark),
                            contentDescription = null,
                        )
                    }
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
                EruditeThemeModel.values().forEach { eruditeTheme ->
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
        SettingsScreen(
            navController = NavController(LocalContext.current),
            currentTheme = EruditeThemeModel.STANDARD_LIGHT,
            selectTheme = {})
    }
}