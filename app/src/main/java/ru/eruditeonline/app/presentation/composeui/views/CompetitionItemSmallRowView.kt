package ru.eruditeonline.app.presentation.composeui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.eruditeonline.app.data.model.competition.CompetitionItemShort
import ru.eruditeonline.app.presentation.composeui.theme.AppTypography
import ru.eruditeonline.app.presentation.composeui.theme.EruditeTheme

@Composable
fun CompetitionItemSmallRowView(
    competitionItem: CompetitionItemShort,
    onClick: (CompetitionItemShort) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.clickable { onClick(competitionItem) }) {
            Row {
                CompetitionImage(
                    imageUrl = competitionItem.icon.orEmpty(),
                    borderStroke = 5.dp,
                    backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp)
                        .size(64.dp),
                )
                Column(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp),
                ) {
                    CompetitionDifficultyView(
                        difficulty = competitionItem.difficulty,
                    )
                    Text(
                        text = competitionItem.title,
                        style = AppTypography.bodyMedium,
                        modifier = Modifier.padding(top = 4.dp),
                    )
                }
            }
            Text(
                text = competitionItem.ages,
                modifier = Modifier.padding(top = 4.dp, start = 12.dp, end = 12.dp, bottom = 8.dp),
                style = AppTypography.bodySmall,
            )
        }
    }
}

@Preview
@Composable
private fun CompetitionItemSmallRowViewPreview() {
    val item = CompetitionItemShort(
        id = 0,
        title = "«Золотая осень»",
        subject = "Литература",
        ages = "5-11 классы, студенты, учителя",
        difficulty = 2,
        icon = "https://erudit-online.ru/assets/images/1/gold_autumn-9c6de671.jpg",
    )
    EruditeTheme {
        CompetitionItemSmallRowView(item, {})
    }
}
