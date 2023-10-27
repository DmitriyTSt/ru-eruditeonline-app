package ru.eruditeonline.app.presentation.composeui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun EruditeTheme(
    eruditeTheme: EruditeThemeModel = EruditeThemeModel.STANDARD_LIGHT,
    content: @Composable() () -> Unit
) {
    val colors = when (eruditeTheme) {
        EruditeThemeModel.STANDARD_LIGHT -> lightColorScheme(ColorStandard.Light)
        EruditeThemeModel.STANDARD_DARK -> darkColorScheme(ColorStandard.Dark)
        EruditeThemeModel.AUTUMN_LIGHT -> lightColorScheme(ColorAutumn.Light)
        EruditeThemeModel.AUTUMN_DARK -> darkColorScheme(ColorAutumn.Dark)
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            window.navigationBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !eruditeTheme.isDarkSchema
        }
    }

    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        content = content
    )
}