package ru.eruditeonline.app.presentation.composeui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.materials.HazeMaterials

@Composable
fun RoundedCardTabRow(
    modifier: Modifier = Modifier,
    containerColor: Color = Color.Transparent,
    tabs: @Composable () -> Unit,
) {
    Row(
        modifier = modifier
            .background(containerColor)
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 12.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        tabs()
    }
}

@Composable
fun RoundedCardTab(
    selected: Boolean,
    onClick: () -> Unit,
    text: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    hazeState: HazeState? = null,
) {
    val tabModifier = modifier
        .then(
            if (hazeState != null) {
                Modifier.hazeEffect(state = hazeState, style = HazeMaterials.thin())
            } else {
                Modifier
            }
        )

    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors().copy(containerColor = Color.Transparent),
        elevation = CardDefaults.elevatedCardElevation(),
    ) {
        Tab(
            selected = selected,
            onClick = onClick,
            modifier = tabModifier,
            selectedContentColor = MaterialTheme.colorScheme.primary,
            unselectedContentColor = LocalContentColor.current,
            text = text,
        )
    }
}
