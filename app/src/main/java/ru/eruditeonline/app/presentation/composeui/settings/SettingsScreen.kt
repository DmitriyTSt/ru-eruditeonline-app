package ru.eruditeonline.app.presentation.composeui.settings

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.composeui.theme.ColorAutumn
import ru.eruditeonline.app.presentation.composeui.theme.ColorStandard
import ru.eruditeonline.app.presentation.composeui.theme.ColorWinter
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
        Column(
            modifier = Modifier
                .padding(innerPaddings)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = "Тема приложения",
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Выберите оформление и режим, который вам нравится",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                Modifier
                    .selectableGroup()
            ) {
                EruditeThemeModel.entries.forEach { eruditeTheme ->
                    ThemeOptionCard(
                        theme = eruditeTheme,
                        selected = currentTheme == eruditeTheme,
                        onSelect = { selectTheme(eruditeTheme) },
                        modifier = Modifier.padding(bottom = 10.dp),
                    )
                }
            }
        }
    }
}

@Composable
private fun ThemeOptionCard(
    theme: EruditeThemeModel,
    selected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val borderColor by animateColorAsState(
        targetValue = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
        animationSpec = spring(),
        label = "themeCardBorder",
    )
    val backgroundColor by animateColorAsState(
        targetValue = if (selected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f) else MaterialTheme.colorScheme.surfaceContainerLow,
        animationSpec = spring(),
        label = "themeCardBackground",
    )

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(16.dp))
            .selectable(
                selected = selected,
                onClick = onSelect,
                role = Role.RadioButton,
            ),
        color = backgroundColor,
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            ThemePreviewSwatch(theme = theme)
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = theme.title,
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = if (theme.isDarkSchema) "Тёмный режим" else "Светлый режим",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            RadioButton(
                selected = selected,
                onClick = null,
            )
        }
    }
}

@Composable
private fun ThemePreviewSwatch(theme: EruditeThemeModel) {
    val palette = theme.previewPalette()
    Surface(
        modifier = Modifier.size(42.dp),
        color = palette.background,
        shape = RoundedCornerShape(12.dp),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(11.dp)
                    .background(color = palette.primary, shape = CircleShape)
            )
            Box(
                modifier = Modifier
                    .size(11.dp)
                    .background(color = palette.secondary, shape = CircleShape)
            )
        }
    }
}

private data class ThemePreviewPalette(
    val primary: Color,
    val secondary: Color,
    val background: Color,
)

private fun EruditeThemeModel.previewPalette(): ThemePreviewPalette {
    val colorScheme = colorScheme()
    return ThemePreviewPalette(
        primary = colorScheme.primary,
        secondary = colorScheme.secondary,
        background = colorScheme.surfaceContainerHighest,
    )
}

private fun EruditeThemeModel.colorScheme(): ColorScheme {
    return when (this) {
        EruditeThemeModel.STANDARD_LIGHT -> ColorStandard.reducedContrastLightColorScheme
        EruditeThemeModel.STANDARD_DARK -> ColorStandard.reducedContrastDarkColorScheme
        EruditeThemeModel.AUTUMN_LIGHT -> ColorAutumn.reducedContrastLightColorScheme
        EruditeThemeModel.AUTUMN_DARK -> ColorAutumn.reducedContrastDarkColorScheme
        EruditeThemeModel.WINTER_LIGHT -> ColorWinter.reducedContrastLightColorScheme
        EruditeThemeModel.WINTER_DARK -> ColorWinter.reducedContrastDarkColorScheme
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