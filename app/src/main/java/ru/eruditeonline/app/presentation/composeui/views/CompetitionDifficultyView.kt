package ru.eruditeonline.app.presentation.composeui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.composeui.theme.AppTypography
import ru.eruditeonline.app.presentation.composeui.theme.EruditeTheme

@Composable
fun CompetitionDifficultyView(difficulty: Int, modifier: Modifier = Modifier) {
//    CompetitionDifficultyImageView(difficulty, modifier)
    CompetitionDifficultyTagView(difficulty, modifier)
}

@Composable
private fun CompetitionDifficultyTagView(difficulty: Int, modifier: Modifier = Modifier) {
    val simpleDifficulty = SimpleDifficulty.fromDifficulty(difficulty)
    Box(modifier.clip(RoundedCornerShape(4.dp))) {
        Text(
            text = simpleDifficulty.text,
            color = Color.White,
            modifier = Modifier
                .background(simpleDifficulty.color)
                .padding(horizontal = 4.dp, vertical = 1.dp),
            style = AppTypography.labelMedium,
        )
    }
}

private sealed class SimpleDifficulty(
    val text: String,
    val color: Color,
) {
    // color green , yellow, red HSV V = 75%
    object Easy : SimpleDifficulty("Easy", Color(0xFF00bf00))
    object Medium : SimpleDifficulty("Medium", Color(0xFFbfbf00))
    object Hard : SimpleDifficulty("Hard", Color(0xFFbf0000))

    companion object {
        fun fromDifficulty(difficulty: Int): SimpleDifficulty {
            return when (difficulty) {
                1, 2 -> Easy
                3, 4 -> Medium
                5 -> Hard
                else -> Medium
            }
        }
    }
}

@Composable
private fun CompetitionDifficultyImageView(difficulty: Int, modifier: Modifier = Modifier) {
    val imageRes = when (difficulty) {
        1 -> R.drawable.ic_rating_1
        2 -> R.drawable.ic_rating_2
        3 -> R.drawable.ic_rating_3
        4 -> R.drawable.ic_rating_4
        5 -> R.drawable.ic_rating_5
        else -> 0
    }
    Image(painter = painterResource(id = imageRes), contentDescription = null, modifier = modifier)
}

@Preview
@Composable
private fun CompetitionDifficultyViewPreview() {
    EruditeTheme {
        Column {
            repeat(5) {
                CompetitionDifficultyView(it + 1)
            }
        }
    }
}