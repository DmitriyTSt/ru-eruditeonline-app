package ru.eruditeonline.app.presentation.composeui.result.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.base.Score
import ru.eruditeonline.app.data.model.test.TestUserResultRow
import ru.eruditeonline.app.presentation.composeui.theme.EruditeTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserResultCard(result: TestUserResultRow, modifier: Modifier = Modifier) {
    val dateText = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.getDefault()).format(result.date)
    val scoreText = stringResource(
        id = R.string.score_template,
        result.score.current,
        result.score.max,
    )
    val scoreProgress = if (result.score.max > 0) {
        (result.score.current.toFloat() / result.score.max).coerceIn(0f, 1f)
    } else {
        0f
    }
    val scoreColor = result.score.color?.let(::Color) ?: MaterialTheme.colorScheme.primary

    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = {},
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(
                    text = result.competitionTitle,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = result.username,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                MetadataBadge(
                    text = dateText,
                )
                MetadataBadge(
                    text = stringResource(R.string.user_result_test_id_template, result.testId),
                    modifier = Modifier.weight(1f),
                )
                MetadataBadge(
                    text = stringResource(R.string.user_result_result_id_template, result.id),
                )
            }

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                MetricCard(
                    label = androidx.compose.ui.res.stringResource(id = R.string.user_result_place_label),
                    value = result.place,
                    modifier = Modifier.weight(1f),
                )
                MetricCard(
                    label = androidx.compose.ui.res.stringResource(id = R.string.user_result_score_label),
                    value = scoreText,
                    modifier = Modifier.weight(1f),
                    progress = scoreProgress,
                    progressColor = scoreColor,
                )
            }
        }
    }
}

@Composable
private fun MetadataBadge(text: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.secondaryContainer,
        shape = RoundedCornerShape(12.dp),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun MetricCard(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    progress: Float? = null,
    progressColor: Color = MaterialTheme.colorScheme.primary,
) {
    Surface(
        modifier = modifier.fillMaxHeight(),
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(14.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            if (progress != null) {
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp),
                    color = progressColor,
                    trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.18f),
                    drawStopIndicator = {},
                )
            } else {
                Spacer(modifier = Modifier.height(6.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UserResultCardPreview() {
    EruditeTheme {
        UserResultCard(
            result = previewUserResult(
                id = 124578,
                place = "I место",
                score = Score(current = 19, max = 20, color = null),
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UserResultCardsPreview() {
    EruditeTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            UserResultCard(
                result = previewUserResult(
                    id = 124578,
                    place = "I место",
                    score = Score(current = 19, max = 20, color = null),
                ),
            )
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.65f))
            UserResultCard(
                result = previewUserResult(
                    id = 124579,
                    place = "Участник",
                    score = Score(current = 11, max = 20, color = null),
                ),
            )
        }
    }
}

private fun previewUserResult(
    id: Int,
    place: String,
    score: Score,
) = TestUserResultRow(
    id = id,
    date = LocalDate.of(2026, 2, 10),
    username = "Иванов Иван Иванович, 8А класс",
    testId = "A446",
    competitionTitle = "Олимпиада по информатике: Позиционные системы счисления и двоичная арифметика",
    place = place,
    score = score,
)
