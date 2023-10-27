package ru.eruditeonline.app.presentation.composeui.dashboard

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.eruditeonline.app.data.model.main.Tagline
import ru.eruditeonline.app.presentation.composeui.theme.EruditeTheme
import ru.eruditeonline.app.presentation.composeui.theme.AppTypography

@Composable
fun TaglineView(tagline: Tagline, modifier: Modifier = Modifier) {
    val taglineColor = Color(tagline.titleColor ?: android.graphics.Color.parseColor("#8bc34a"))
    val taglineLightColor = Color(tagline.titleColor ?: android.graphics.Color.parseColor("#c7ff86"))
    Card(modifier = modifier) {
        Row(Modifier.padding(start = 12.dp, top = 12.dp, end = 12.dp, bottom = 16.dp)) {
            Card(
                modifier = Modifier
                    .size(64.dp)
                    .border(2.dp, taglineColor, CardDefaults.shape),
                colors = CardDefaults.cardColors(
                    containerColor = taglineLightColor,
                ),
            ) {
                AsyncImage(
                    model = tagline.icon,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                )
            }
            Column(Modifier.padding(start = 16.dp)) {
                Text(
                    text = tagline.title,
                    style = AppTypography.titleMedium,
                    color = taglineColor,
                )
                Text(
                    text = tagline.text,
                    style = AppTypography.bodySmall,
                )
            }
        }
    }
}

@Preview
@Composable
private fun TaglinePreview() {
    val tagline = Tagline(
        title = "Мгновенный диплом!",
        text = "Результат и наградные материалы доступны сразу после прохождения конкурса",
        icon = "https://erudit-online.ru/files/design/slogan_1.png",
        titleColor = android.graphics.Color.parseColor("#8bc34a"),
        url = null,
    )
    EruditeTheme {
        TaglineView(tagline)
    }
}