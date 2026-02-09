package ru.eruditeonline.app.presentation.composeui.base

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

/**
 * Отступ снизу который нужно поставить контенту, из-за наличия на экране нижнего меню [BottomNavigationScreen]
 */
val LocalBottomNavigationPadding = staticCompositionLocalOf { 0.dp }