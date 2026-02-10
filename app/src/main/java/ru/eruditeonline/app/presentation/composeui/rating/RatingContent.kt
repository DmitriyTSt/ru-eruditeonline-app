package ru.eruditeonline.app.presentation.composeui.rating

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.rating.RatingRow
import ru.eruditeonline.app.presentation.composeui.base.BottomNavigationSpaceWithInset
import ru.eruditeonline.app.presentation.composeui.theme.AppTypography

@Composable
internal fun RatingList(
    rating: List<RatingRow>,
    innerPaddings: PaddingValues,
    dateSelectorHeightDp: Dp,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            // дополнительно отступ самого селектора и еще немного для равества отступов сверху и снизу от селектора
            Spacer(Modifier.height(innerPaddings.calculateTopPadding() + dateSelectorHeightDp + 8.dp))
        }
        items(items = rating) { ratingRow ->
            RatingRowCard(ratingRow = ratingRow)
        }
        item {
            BottomNavigationSpaceWithInset(innerPaddings = innerPaddings, additionalPadding = 16.dp)
        }
    }
}

@Composable
private fun RatingRowCard(ratingRow: RatingRow) {
    OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.secondaryContainer,
                ) {
                    Text(
                        text = "#${ratingRow.rank}",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = AppTypography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                    )
                }

                Column(modifier = Modifier.padding(start = 10.dp)) {
                    RankChangeView(
                        rank = ratingRow.rank,
                        oldRank = ratingRow.oldRank,
                        modifier = Modifier.padding(top = 4.dp),
                    )
                    Row(verticalAlignment = Alignment.Top) {
                        AsyncImage(
                            model = ratingRow.countryIcon,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(top = 3.dp)
                                .size(18.dp),
                        )
                        Text(
                            text = ratingRow.username,
                            modifier = Modifier.padding(start = 8.dp),
                            style = AppTypography.bodyLarge,
                            fontWeight = FontWeight.Medium,
                        )
                    }
                }
            }

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
            ) {
                Text(
                    text = ratingRow.score.toString(),
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    style = AppTypography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}

@Composable
private fun RankChangeView(
    rank: Int,
    oldRank: Int?,
    modifier: Modifier = Modifier,
) {
    val isVisible = oldRank != null && (oldRank != rank || oldRank == 0)
    if (!isVisible || oldRank == null) return

    val diff = oldRank - rank
    val isPositive = diff > 0 || oldRank == 0
    val positiveColor = Color(0xFF00AA00)
    val negativeColor = Color(0xFFFF0000)
    val ratingColor = if (isPositive) positiveColor else negativeColor
    val iconRes = if (isPositive) R.drawable.ic_rank_positive else R.drawable.ic_rank_negative
    val text = if (oldRank == 0) {
        stringResource(R.string.new_rank_label)
    } else {
        if (diff > 0) "+$diff" else diff.toString()
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(14.dp),
            tint = ratingColor,
        )
        Text(
            text = text,
            style = AppTypography.bodyMedium,
            color = ratingColor,
            modifier = Modifier.padding(start = 2.dp),
        )
    }
}

@Composable
internal fun EmptyRatingView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(R.string.rating_empty_title),
            style = AppTypography.titleSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
        Text(
            text = stringResource(R.string.rating_empty_comment),
            style = AppTypography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 8.dp),
        )
    }
}
