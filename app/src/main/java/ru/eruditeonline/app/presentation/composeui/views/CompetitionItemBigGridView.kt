package ru.eruditeonline.app.presentation.composeui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.eruditeonline.app.data.model.competition.CompetitionItemShort
import ru.eruditeonline.app.presentation.composeui.theme.AppTypography
import ru.eruditeonline.app.presentation.composeui.theme.EruditeTheme

@Composable
fun CompetitionItemBigGridView(
    competitionItem: CompetitionItemShort,
    onClick: (CompetitionItemShort) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .clickable { onClick(competitionItem) }
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                CompetitionImage(
                    imageUrl = competitionItem.icon.orEmpty(),
                    borderStroke = 12.dp,
                    backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier
                        .fillMaxWidth(),
                )
                Box(
                    modifier = Modifier
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0f),
                                    MaterialTheme.colorScheme.surfaceVariant,
                                    MaterialTheme.colorScheme.surfaceVariant,
                                )
                            )
                        )
                        .fillMaxWidth()
                        .height(60.dp)
                        .align(Alignment.BottomCenter),
                )
                CompetitionDifficultyView(
                    difficulty = competitionItem.difficulty,
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .align(Alignment.BottomCenter),
                )
            }
            Text(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .fillMaxWidth(),
                text = "${competitionItem.subject} ${competitionItem.title}",
                style = AppTypography.bodyMedium,
                textAlign = TextAlign.Center,
            )
            Text(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                    .fillMaxWidth(),
                text = competitionItem.ages,
                style = AppTypography.bodySmall,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview
@Composable
private fun CompetitionItemBigGridViewPreview() {
    val item = CompetitionItemShort(
        id = 0,
        title = "«Золотая осень»",
        subject = "Литература",
        ages = "5-11 классы, студенты, учителя",
        difficulty = 2,
        icon = "https://erudit-online.ru/assets/images/1/gold_autumn-9c6de671.jpg",
    )
    EruditeTheme {
        CompetitionItemBigGridView(
            competitionItem = item,
            onClick = {},
            modifier = Modifier
                .width(160.dp)
        )
    }
}
