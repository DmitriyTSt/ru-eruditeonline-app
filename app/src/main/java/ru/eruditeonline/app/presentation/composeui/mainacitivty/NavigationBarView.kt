package ru.eruditeonline.app.presentation.composeui.mainacitivty

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.materials.HazeMaterials
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.composeui.base.BaseScreen
import ru.eruditeonline.app.presentation.composeui.model.BottomMenuItem

@Composable
fun NavigationBarView(
    currentScreen: BaseScreen,
    backStack: SnapshotStateList<BaseScreen>,
    hazeState: HazeState,
    modifier: Modifier = Modifier,
) {
    val elevation = dimensionResource(id = R.dimen.default_card_elevation)
    val horizontalItemPadding = 8.dp

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(40.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
    ) {
        val containerColor = Color.Transparent
        Surface(
            color = containerColor,
            contentColor = MaterialTheme.colorScheme.contentColorFor(containerColor),
            tonalElevation = NavigationBarDefaults.Elevation,
            modifier = Modifier.hazeEffect(state = hazeState, style = HazeMaterials.thin()),
        ) {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .selectableGroup(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(Modifier.width(horizontalItemPadding))
                listOf(
                    BottomMenuItem.Dashboard,
                    BottomMenuItem.Competitions,
                    BottomMenuItem.Rating,
                    BottomMenuItem.Profile,
                ).forEach { item ->
                    NavigationBarItem(
                        selected = item.screen::class == currentScreen::class,
                        onClick = {
                            if (item.screen::class != currentScreen::class) {
                                backStack.add(item.screen)
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = item.iconRes),
                                contentDescription = null,
                            )
                        },
                        label = {
                            Text(text = stringResource(id = item.titleRes))
                        },
                    )
                }
                Spacer(Modifier.width(horizontalItemPadding))
            }
        }
    }
}
