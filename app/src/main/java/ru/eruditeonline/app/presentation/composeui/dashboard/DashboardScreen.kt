package ru.eruditeonline.app.presentation.composeui.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavController
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.data.model.competition.CompetitionItemShort
import ru.eruditeonline.app.data.model.main.MainSection
import ru.eruditeonline.app.presentation.composeui.model.Screen
import ru.eruditeonline.app.presentation.composeui.views.TopAppBarView
import ru.eruditeonline.app.presentation.ui.dashboard.DashboardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController, viewModel: DashboardViewModel) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val mainSectionsState: LoadableState<List<MainSection>> by viewModel.mainSectionsLiveData
        .observeAsState(LoadableState.Loading())

    LaunchedEffect(Unit) {
        viewModel.loadMainSections()
    }

    Scaffold(
        topBar = { TopAppBarView(title = "Эрудит.Онлайн", scrollBehavior = scrollBehavior) },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        contentWindowInsets = WindowInsets.statusBars,
    ) { innerPaddings ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings)
        ) {
            if (mainSectionsState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            if (mainSectionsState.isSuccess) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    (mainSectionsState as LoadableState.Success).data.forEach { mainSection ->
                        MainSectionView(
                            mainSection = mainSection,
                            onCompetitionClick = { navController.navigate(Screen.Competition.route(1)) }
                        )
                    }
                }
            }
            if (mainSectionsState.isError) {
                Text(text = (mainSectionsState as LoadableState.Error).error.message, modifier = Modifier.align(Alignment.Center))
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