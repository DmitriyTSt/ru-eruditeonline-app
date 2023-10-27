package ru.eruditeonline.app.presentation.composeui.dashboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.eruditeonline.app.data.model.competition.CompetitionItemShort
import ru.eruditeonline.app.data.model.main.CompetitionViewType
import ru.eruditeonline.app.data.model.main.MainSection
import ru.eruditeonline.app.presentation.composeui.theme.AppTypography
import ru.eruditeonline.app.presentation.composeui.views.CompetitionItemBigRowView
import ru.eruditeonline.app.presentation.composeui.views.CompetitionItemSmallGridView

@Composable
fun MainSectionCompetitionsBlock(
    mainSection: MainSection.CompetitionsBlock,
    onCompetitionClick: (CompetitionItemShort) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Text(
            text = mainSection.title,
            modifier = Modifier.padding(horizontal = 16.dp),
            style = AppTypography.titleLarge,
        )
        Spacer(modifier = Modifier.height(16.dp))
        when (mainSection.viewType) {
            CompetitionViewType.ROW -> {
                CompetitionItemsRow(mainSection, onCompetitionClick)
            }
            CompetitionViewType.CARD -> {
                CompetitionItemsGrid(mainSection, onCompetitionClick)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CompetitionItemsRow(mainSection: MainSection.CompetitionsBlock, onCompetitionClick: (CompetitionItemShort) -> Unit) {
    HorizontalPager(
        pageCount = mainSection.items.size,
        contentPadding = PaddingValues(horizontal = 12.dp),
    ) {
        Row {
            Spacer(modifier = Modifier.width(4.dp))
            CompetitionItemBigRowView(
                competitionItem = mainSection.items[it],
                onClick = { onCompetitionClick(it) },
                modifier = Modifier.weight(1f),
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
private fun CompetitionItemsGrid(mainSection: MainSection.CompetitionsBlock, onCompetitionClick: (CompetitionItemShort) -> Unit) {
    Column(Modifier.fillMaxWidth()) {
        mainSection.items.chunked(2).forEach { pair ->
            val left = pair.first()
            val right = pair.getOrNull(1)
            Row(
                Modifier
                    .height(intrinsicSize = IntrinsicSize.Max)
                    .padding(horizontal = 16.dp),
            ) {
                val competitionModifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                CompetitionItemSmallGridView(
                    competitionItem = left,
                    onClick = { onCompetitionClick(it) },
                    modifier = competitionModifier,
                )
                Spacer(modifier = Modifier.width(16.dp))
                if (right != null) {
                    CompetitionItemSmallGridView(
                        competitionItem = right,
                        onClick = { onCompetitionClick(it) },
                        modifier = competitionModifier,
                    )
                } else {
                    Spacer(
                        modifier = competitionModifier,
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}