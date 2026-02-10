package ru.eruditeonline.app.presentation.composeui.base

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

/**
 * Нижний отступ в размере нижнего инсета (Scafold - берется из PaddingValues),
 * размера нижнего меню (если есть) и дополнительного отступа
 */
@Composable
fun BottomNavigationSpaceWithInset(innerPaddings: PaddingValues, additionalPadding: Dp) {
    Spacer(
        Modifier.height(
            height = innerPaddings.calculateBottomPadding() +
                LocalBottomNavigationPadding.current +
                additionalPadding
        )
    )
}