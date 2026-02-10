package ru.eruditeonline.app.presentation.composeui.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.materials.HazeMaterials
import dev.chrisbanes.haze.rememberHazeState
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.data.model.competition.CompetitionItemShort
import ru.eruditeonline.app.data.model.main.MainSection
import ru.eruditeonline.app.presentation.composeui.base.BottomNavigationSpaceWithInset
import ru.eruditeonline.app.presentation.composeui.base.LocalBottomNavigationPadding
import ru.eruditeonline.app.presentation.composeui.base.ObserveDestinations
import ru.eruditeonline.app.presentation.composeui.base.appViewModel
import ru.eruditeonline.app.presentation.composeui.theme.AppTypography
import ru.eruditeonline.app.presentation.composeui.views.StateFlipperView
import ru.eruditeonline.app.presentation.ui.dashboard.DashboardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(viewModel: DashboardViewModel = appViewModel()) {
    viewModel.ObserveDestinations()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val mainSectionsState: LoadableState<List<MainSection>> by viewModel.mainSectionsLiveData
        .observeAsState(LoadableState.Loading())

    val isDebugButtonVisibleState by viewModel.isDebugButtonVisibleLiveData.observeAsState(LoadableState.Loading())

    LaunchedEffect(Unit) {
        viewModel.callOperations {
            viewModel.initDebugButton()
            viewModel.loadMainSections()
        }
    }

    Scaffold(
        contentWindowInsets = WindowInsets(),
    ) { innerPaddings ->
        val hazeState = rememberHazeState()
        Box(Modifier.fillMaxSize()) {
            StateFlipperView(
                state = mainSectionsState,
                onRetryClick = {
                    viewModel.loadMainSections()
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPaddings)
                    .hazeSource(hazeState),
            ) { mainSections ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    val density = LocalDensity.current
                    val topInset = with(density) { WindowInsets.statusBars.getTop(density).toDp() }
                    Spacer(
                        Modifier
                            .height(TopAppBarDefaults.TopAppBarExpandedHeight + topInset)
                    )
                    mainSections.forEach { mainSection ->
                        MainSectionView(
                            mainSection = mainSection,
                            onCompetitionClick = { viewModel.openCompetition(it) }
                        )
                    }
                    BottomNavigationSpaceWithInset(innerPaddings, 16.dp)
                }
            }
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = AppTypography.titleLarge,
                    )
                },
                modifier = Modifier
                    .hazeEffect(state = hazeState, style = HazeMaterials.thin()),
                actions = {
                    if ((isDebugButtonVisibleState as? LoadableState.Success)?.data == true) {
                        TextButton(onClick = { viewModel.openDebug() }) {
                            Text(text = stringResource(R.string.debug_title), style = AppTypography.bodyMedium)
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors().copy(
                    containerColor = Color.Transparent,
                ),
                scrollBehavior = scrollBehavior
            )
        }
    }
}

@Composable
fun MainSectionView(mainSection: MainSection, onCompetitionClick: (CompetitionItemShort) -> Unit, modifier: Modifier = Modifier) {
    when (mainSection) {
        is MainSection.CompetitionsBlock -> MainSectionCompetitionsBlock(mainSection, onCompetitionClick, modifier)
        is MainSection.TaglineBlock -> MainSectionTaglineBlock(mainSection, modifier)
    }
}