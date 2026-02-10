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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            Spacer(Modifier.height(innerPaddings.calculateTopPadding() + dateSelectorHeightDp + 16.dp))
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
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${ratingRow.rank}.",
                        style = AppTypography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                    )
                    RankChangeView(
                        rank = ratingRow.rank,
                        oldRank = ratingRow.oldRank,
                        modifier = Modifier.padding(start = 8.dp),
                    )
                }
                Text(
                    text = ratingRow.score.toString(),
                    style = AppTypography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = ratingRow.countryIcon,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                )
                Text(
                    text = ratingRow.username,
                    modifier = Modifier.padding(start = 8.dp),
                    style = AppTypography.bodyLarge,
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
        )
        Text(
            text = text,
            style = AppTypography.bodyMedium,
            color = if (isPositive) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.error,
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
