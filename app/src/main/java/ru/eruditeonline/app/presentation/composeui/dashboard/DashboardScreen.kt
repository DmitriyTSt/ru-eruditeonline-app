package ru.eruditeonline.app.presentation.composeui.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.data.model.competition.CompetitionItemShort
import ru.eruditeonline.app.data.model.main.MainSection
import ru.eruditeonline.app.presentation.composeui.model.Screen
import ru.eruditeonline.app.presentation.composeui.theme.AppTypography
import ru.eruditeonline.app.presentation.composeui.views.StateFlipperView
import ru.eruditeonline.app.presentation.ui.dashboard.DashboardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController, viewModel: DashboardViewModel) {
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
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = AppTypography.titleLarge,
                    )
                },
                actions = {
                    if ((isDebugButtonVisibleState as? LoadableState.Success)?.data == true) {
                        TextButton(onClick = { navController.navigate(Screen.Debug.route) }) {
                            Text(text = stringResource(R.string.debug_title), style = AppTypography.bodyMedium)
                        }
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        contentWindowInsets = WindowInsets.statusBars,
    ) { innerPaddings ->
        StateFlipperView(
            state = mainSectionsState,
            onRetryClick = {
                viewModel.loadMainSections()
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings),
        ) { mainSections ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                mainSections.forEach { mainSection ->
                    MainSectionView(
                        mainSection = mainSection,
                        onCompetitionClick = { navController.navigate(Screen.Competition.route(1)) }
                    )
                }
            }
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