package ru.eruditeonline.app.presentation.composeui.result.common

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.mapper.orDefault
import ru.eruditeonline.app.data.model.test.TestCommonResultRow
import ru.eruditeonline.app.presentation.composeui.theme.AppTypography
import ru.eruditeonline.app.presentation.composeui.theme.EruditeTheme
import ru.eruditeonline.app.presentation.managers.DateFormatter

@Composable
fun TopResultView(topResult: TestCommonResultRow, onClick: (TestCommonResultRow) -> Unit, modifier: Modifier = Modifier) {
    val date = DateFormatter().formatStandardDate(topResult.date)
    Column(
        modifier = modifier.clickable { onClick(topResult) }
    ) {
        Row(Modifier.padding(bottom = 8.dp, start = 16.dp, end = 16.dp)) {
            ResultPlace(topResult.resultText, Modifier.padding(top = 16.dp, end = 12.dp))
            Text(
                text = topResult.competitionTitle,
                modifier = Modifier.padding(top = 8.dp),
                style = AppTypography.bodyLarge,
            )
        }
        Text(
            text = date,
            modifier = Modifier.padding(horizontal = 16.dp),
            style = AppTypography.bodySmall,
        )
        Text(
            text = topResult.username,
            modifier = Modifier.padding(horizontal = 16.dp),
            style = AppTypography.bodyMedium,
        )
        Text(
            text = topResult.city,
            modifier = Modifier.padding(horizontal = 16.dp),
            style = AppTypography.bodyMedium,
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
private fun ResultPlace(resultText: String, modifier: Modifier = Modifier) {
    val place = when {
        resultText.contains("III") -> 3
        resultText.contains("II") -> 2
        resultText.contains("I") -> 1
        else -> resultText.let {
            val placeStart = it.indexOf("(") + 1
            it.substring(placeStart, it.indexOf(" ", placeStart).takeIf { it > 0 } ?: it.length).toIntOrNull()
        }
    }
    val iconTint = when (place) {
        1 -> Color(0xffffd700)
        2 -> Color(0xffc0c0c0)
        3 -> Color(0xffcd7f32)
        else -> null
    }
    if (place.orDefault(5) < 4) {
        if (iconTint != null) {
            Icon(
                painter = painterResource(id = R.drawable.ic_menu_rating_inactive),
                contentDescription = null,
                modifier = modifier,
                tint = iconTint,
            )
        }
    } else if (place != null) {
        Box(modifier = modifier) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(24.dp)
                    .border(1.dp, Color.Gray, CircleShape)
            ) {
                Text(
                    text = place.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 1.dp, end = 0.75.dp),
                    textAlign = TextAlign.Center,
                )
            }
        }
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
