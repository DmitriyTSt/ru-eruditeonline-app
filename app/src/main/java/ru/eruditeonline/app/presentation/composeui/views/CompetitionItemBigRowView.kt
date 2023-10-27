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
import androidx.compose.ui.unit.dp
import ru.eruditeonline.app.data.model.competition.CompetitionItemShort
import ru.eruditeonline.app.presentation.composeui.theme.AppTypography

@Composable
fun CompetitionItemBigRowView(
    competitionItem: CompetitionItemShort,
    onClick: (CompetitionItemShort) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier) {
        Row(modifier = Modifier.clickable { onClick(competitionItem) }) {
            CompetitionImage(
                imageUrl = competitionItem.icon.orEmpty(),
                borderStroke = 10.dp,
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier
                    .padding(start = 12.dp, top = 12.dp, bottom = 12.dp)
                    .size(124.dp),
            )
            Column(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp),
            ) {
                Text(
                    text = competitionItem.title,
                    style = AppTypography.bodyMedium,
                )
                CompetitionDifficultyView(
                    difficulty = competitionItem.difficulty,
                    modifier = Modifier.padding(top = 4.dp),
                )
                Text(
                    text = competitionItem.ages,
                    modifier = Modifier.padding(top = 4.dp),
                    style = AppTypography.bodySmall,
                )
            }
        }
    }
}
