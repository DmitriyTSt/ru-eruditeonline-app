package ru.eruditeonline.app.presentation.composeui.competition.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.presentation.composeui.theme.AppTypography
import ru.eruditeonline.app.presentation.composeui.views.CompetitionDifficultyView
import ru.eruditeonline.app.presentation.composeui.views.CompetitionImage
import ru.eruditeonline.app.presentation.composeui.views.NavigationIcon
import ru.eruditeonline.app.presentation.composeui.views.StateFlipperView
import ru.eruditeonline.app.presentation.ui.competition.detail.CompetitionDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompetitionScreen(id: Int, navController: NavController, viewModel: CompetitionDetailViewModel) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val competitionItemState by viewModel.competitionItemLiveData.observeAsState(LoadableState.Loading())

    LaunchedEffect(id) {
        viewModel.callOperations {
            viewModel.loadCompetitionItem(id)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = competitionItemState.getOrNull()?.subject.orEmpty())
                },
                navigationIcon = {
                    NavigationIcon(navController)
                },
                scrollBehavior = scrollBehavior,
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        contentWindowInsets = WindowInsets.statusBars,
    ) { innerPaddings ->
        StateFlipperView(
            state = competitionItemState,
            onRetryClick = { viewModel.loadCompetitionItem(id) },
            modifier = Modifier.fillMaxSize().padding(innerPaddings),
        ) { competitionItem ->
            Column(Modifier.padding(16.dp)) {
                CompetitionImage(
                    imageUrl = competitionItem.icon.orEmpty(),
                    borderStroke = 12.dp,
                    backgroundColor = MaterialTheme.colorScheme.surface,
                    modifier = Modifier
                        .size(164.dp)
                        .align(Alignment.CenterHorizontally),
                )
                Text(text = competitionItem.title, style = AppTypography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(R.string.competition_ages_template, competitionItem.ages),
                    style = AppTypography.bodyLarge,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = stringResource(R.string.difficulty), style = AppTypography.bodyLarge)
                    CompetitionDifficultyView(
                        difficulty = competitionItem.difficulty,
                        modifier = Modifier.padding(start = 8.dp),
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                if (!competitionItem.description.isNullOrEmpty()) {
                    Text(text = competitionItem.description, style = AppTypography.bodySmall)
                }
            }
        }
    }
}