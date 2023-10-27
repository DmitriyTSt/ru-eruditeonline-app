package ru.eruditeonline.app.presentation.composeui.views

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage

@Composable
fun CompetitionImage(
    imageUrl: String,
    borderStroke: Dp,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        modifier = modifier
            .aspectRatio(1f)
            .clip(CircleShape)
            // у картинок есть белая обводка, заменяем на цвет фона, где встраиваем
            .border(borderStroke, backgroundColor, CircleShape),
    )
}