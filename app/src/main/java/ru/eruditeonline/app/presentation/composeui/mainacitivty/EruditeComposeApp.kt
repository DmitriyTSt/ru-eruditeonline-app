package ru.eruditeonline.app.presentation.composeui.mainacitivty

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.composeui.auth.login.LoginScreen
import ru.eruditeonline.app.presentation.composeui.auth.registration.RegistrationScreen
import ru.eruditeonline.app.presentation.composeui.base.BaseScreen
import ru.eruditeonline.app.presentation.composeui.base.LocalBackStack
import ru.eruditeonline.app.presentation.composeui.base.LocalViewModelFactory
import ru.eruditeonline.app.presentation.composeui.base.appViewModel
import ru.eruditeonline.app.presentation.composeui.competition.detail.Competition
import ru.eruditeonline.app.presentation.composeui.competition.detail.CompetitionScreen
import ru.eruditeonline.app.presentation.composeui.competition.filter.CompetitionFilterScreen
import ru.eruditeonline.app.presentation.composeui.competition.items.CompetitionsScreen
import ru.eruditeonline.app.presentation.composeui.dashboard.Dashboard
import ru.eruditeonline.app.presentation.composeui.dashboard.DashboardScreen
import ru.eruditeonline.app.presentation.composeui.debug.DebugScreen
import ru.eruditeonline.app.presentation.composeui.model.Screen
import ru.eruditeonline.app.presentation.composeui.profile.ProfileScreen
import ru.eruditeonline.app.presentation.composeui.rating.RatingScreen
import ru.eruditeonline.app.presentation.composeui.result.common.CommonResultListScreen
import ru.eruditeonline.app.presentation.composeui.settings.SettingsScreen
import ru.eruditeonline.app.presentation.composeui.theme.EruditeTheme
import ru.eruditeonline.app.presentation.composeui.theme.EruditeThemeModel

@Composable
fun EruditeComposeApp(startScreen: BaseScreen, viewModelFactory: ViewModelProvider.Factory) {
    var eruditeTheme by remember { mutableStateOf(EruditeThemeModel.STANDARD_LIGHT) }
    val backStack = remember { mutableStateListOf(startScreen) }
    val hazeState = rememberHazeState()

    EruditeTheme(
        eruditeTheme = eruditeTheme,
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                CompositionLocalProvider(
                    LocalBackStack provides backStack,
                    LocalViewModelFactory provides viewModelFactory,
                ) {
                    NavDisplay(
                        backStack = backStack,
                        onBack = { backStack.removeLastOrNull() },
                        entryProvider = entryProvider {
                            entry<Dashboard> { DashboardScreen() }
                            entry<Competition> { CompetitionScreen(it.id) }
                        },
                    )
                }
                NavHost(
                    navController = navController,
                    startDestination = startScreen?.route ?: Screen.Dashboard.route,
                    modifier = Modifier
                        .fillMaxSize()
                        .hazeSource(hazeState),
                ) {
                    composable(Screen.Dashboard.route) {
                        DashboardScreen(
                            navController = navController,
                            viewModel = appViewModel(viewModelFactory),
                        )
                    }
                    composable(Screen.Competitions.route) {
                        CompetitionsScreen(
                            navController = navController,
                            viewModel = appViewModel(viewModelFactory),
                        )
                    }
                    composable(Screen.CompetitionFilter.route) {
                        CompetitionFilterScreen(
                            navController = navController,
                            viewModel = appViewModel(viewModelFactory),
                        )
                    }
                    composable(Screen.Rating.route) { RatingScreen(/*...*/) }
                    composable(Screen.Profile.route) {
                        ProfileScreen(
                            navController = navController,
                            viewModel = appViewModel(viewModelFactory),
                            viewModelFactory = viewModelFactory,
                        )
                    }
                    composable(Screen.Competition.route, Screen.Competition.arguments) { backStackEntry ->
                        CompetitionScreen(
                            id = backStackEntry.arguments?.getInt("id") ?: 0,
                            navController = navController,
                            viewModel = appViewModel(viewModelFactory),
                        )
                    }
                    composable(Screen.SearchResults.route) { }
                    composable(Screen.UserResults.route) { }
                    composable(Screen.CommonResults.route) {
                        CommonResultListScreen(navController, appViewModel(viewModelFactory))
                    }
                    composable(Screen.Settings.route) { SettingsScreen(navController, eruditeTheme) { eruditeTheme = it } }
                    composable(Screen.Info.route) { }
                    composable(Screen.Debug.route) {
                        DebugScreen(
                            navController = navController,
                            viewModel = appViewModel(viewModelFactory),
                        )
                    }
                    composable(Screen.Login.route) {
                        LoginScreen(navController, appViewModel(viewModelFactory))
                    }
                    composable(Screen.Registration.route) {
                        RegistrationScreen(navController, appViewModel(viewModelFactory))
                    }
                }

                val density = LocalDensity.current
                val horizontalPadding = 24.dp
                val bottomPadding = dimensionResource(id = R.dimen.bottom_navigation_view_margin_bottom)
                NavigationBarView(
                    navController,
                    hazeState,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = horizontalPadding)
                        .padding(bottom = bottomPadding + with(density) {
                            WindowInsets.navigationBars.getBottom(density).toDp()
                        }),
                )
            }
        }
    }
}