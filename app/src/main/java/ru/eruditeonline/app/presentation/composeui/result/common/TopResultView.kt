package ru.eruditeonline.app.presentation.composeui.result.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.mapper.orDefault
import ru.eruditeonline.app.data.model.test.TestCommonResultRow
import ru.eruditeonline.app.presentation.composeui.theme.AppTypography
import ru.eruditeonline.app.presentation.composeui.theme.EruditeTheme
import ru.eruditeonline.app.presentation.managers.DateFormatter
import java.time.LocalDate

@Composable
fun TopResultView(topResult: TestCommonResultRow, onClick: (TestCommonResultRow) -> Unit, modifier: Modifier = Modifier) {
    val date = DateFormatter().formatStandardDate(topResult.date)
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick(topResult) },
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surfaceContainerLow,
        tonalElevation = 1.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.Top,
        ) {
            ResultPlace(
                resultText = topResult.resultText,
                modifier = Modifier.padding(top = 2.dp, end = 12.dp),
            )
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = topResult.competitionTitle,
                    style = AppTypography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                )
                Text(
                    text = topResult.resultText,
                    style = AppTypography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = date,
                        style = AppTypography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = "(${topResult.city})",
                        style = AppTypography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                Text(
                    text = topResult.username,
                    style = AppTypography.bodyMedium,
                )
            }
        }
    }
}

@Composable
private fun ResultPlace(resultText: String, modifier: Modifier = Modifier) {
    val place = extractPlace(resultText)
    val iconTint = when (place) {
        1 -> Color(0xFFFFD700)
        2 -> Color(0xFFC0C0C0)
        3 -> Color(0xFFCD7F32)
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }
    Box(
        modifier = modifier
            .size(34.dp)
            .defaultMinSize(minWidth = 34.dp, minHeight = 34.dp),
        contentAlignment = Alignment.Center,
    ) {
        if (place.orDefault(Int.MAX_VALUE) <= 3) {
            Icon(
                painter = painterResource(id = R.drawable.ic_menu_rating_inactive),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = iconTint,
            )
        } else if (place != null) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(MaterialTheme.colorScheme.surfaceContainerHighest),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = place.toString(),
                    style = AppTypography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        } else {
            Icon(
                painter = painterResource(id = R.drawable.ic_menu_rating_inactive),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colorScheme.outline,
            )
        }
    }
}

private fun extractPlace(resultText: String): Int? {
    val normalizedResult = resultText.uppercase()
    return when {
        normalizedResult.contains("III") -> 3
        normalizedResult.contains("II") -> 2
        normalizedResult.contains("I") -> 1
        else -> {
            val bracketNumber = Regex("""\((\d+)""").find(resultText)?.groupValues?.getOrNull(1)?.toIntOrNull()
            bracketNumber ?: Regex("""\d+""").find(resultText)?.value?.toIntOrNull()
        }
    }
}

@Preview
@Composable
private fun TopResultViewPreview() {
    EruditeTheme {
        TopResultView(
            topResult = TestCommonResultRow(
                date = LocalDate.now(),
                username = "Иван Петров",
                city = "Москва",
                countryIcon = "",
                competitionId = 1,
                competitionTitle = "Всероссийская олимпиада по математике",
                resultText = "I место (1 из 230)",
            ),
            onClick = {},
        )
    }
}

@Preview
@Composable
fun ResultPlacePreview1() {
    EruditeTheme {
        ResultPlace("I")
    }
}

@Preview
@Composable
fun ResultPlacePreview2() {
    EruditeTheme {
        ResultPlace("II")
    }
}

@Preview
@Composable
fun ResultPlacePreview3() {
    EruditeTheme {
        ResultPlace("III")
    }
}

@Preview
@Composable
fun ResultPlacePreview4() {
    EruditeTheme {
        ResultPlace("4")
    }
}
