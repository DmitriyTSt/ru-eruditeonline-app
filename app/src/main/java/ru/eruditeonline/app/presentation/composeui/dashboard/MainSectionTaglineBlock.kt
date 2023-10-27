package ru.eruditeonline.app.presentation.composeui.dashboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.eruditeonline.app.data.model.main.MainSection

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainSectionTaglineBlock(mainSection: MainSection.TaglineBlock, modifier: Modifier = Modifier) {
    HorizontalPager(
        pageCount = mainSection.taglines.size,
        contentPadding = PaddingValues(12.dp),
        modifier = modifier,
    ) {
        Row {
            Spacer(modifier = Modifier.width(4.dp))
            TaglineView(
                tagline = mainSection.taglines[it],
                modifier = Modifier
                    .weight(1f)
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}
