package ru.eruditeonline.app.presentation.composeui.mainacitivty

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.eruditeonline.app.presentation.composeui.competition.detail.CompetitionScreen
import ru.eruditeonline.app.presentation.composeui.competition.filter.CompetitionFilterScreen
import ru.eruditeonline.app.presentation.composeui.competition.items.CompetitionsScreen
import ru.eruditeonline.app.presentation.composeui.dashboard.DashboardScreen
import ru.eruditeonline.app.presentation.composeui.debug.DebugScreen
import ru.eruditeonline.app.presentation.composeui.model.Screen
import ru.eruditeonline.app.presentation.composeui.profile.ProfileScreen
import ru.eruditeonline.app.presentation.composeui.rating.RatingScreen
import ru.eruditeonline.app.presentation.composeui.settings.SettingsScreen
import ru.eruditeonline.app.presentation.composeui.theme.EruditeTheme
import ru.eruditeonline.app.presentation.composeui.theme.EruditeThemeModel
import ru.eruditeonline.app.presentation.ui.competition.detail.CompetitionDetailViewModel
import ru.eruditeonline.app.presentation.ui.competition.filter.CompetitionFilterViewModel
import ru.eruditeonline.app.presentation.ui.competition.items.CompetitionItemsViewModel
import ru.eruditeonline.app.presentation.ui.dashboard.DashboardViewModel
import ru.eruditeonline.app.presentation.ui.debug.DebugViewModel
import ru.eruditeonline.app.presentation.ui.profile.ProfileViewModel

@Composable
fun EruditeComposeApp(startScreen: Screen?, viewModelFactory: ViewModelProvider.Factory) {
    var eruditeTheme by remember { mutableStateOf(EruditeThemeModel.STANDARD_LIGHT) }
    val navController = rememberNavController()

    EruditeTheme(
        eruditeTheme = eruditeTheme,
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Screen.Dashboard.route,
                    modifier = Modifier.weight(1f),
                ) {
                    composable(Screen.Dashboard.route) {
                        DashboardScreen(
                            navController = navController,
                            viewModel = viewModel {
                                viewModelFactory.create(DashboardViewModel::class.java)
                            },
                        )
                    }
                    composable(Screen.Competitions.route) {
                        CompetitionsScreen(
                            navController = navController,
                            viewModel = viewModel {
                                viewModelFactory.create(CompetitionItemsViewModel::class.java)
                            },
                        )
                    }
                    composable(Screen.CompetitionFilter.route) {
                        CompetitionFilterScreen(
                            navController = navController,
                            viewModel = viewModel {
                                viewModelFactory.create(CompetitionFilterViewModel::class.java)
                            },
                        )
                    }
                    composable(Screen.Rating.route) { RatingScreen(/*...*/) }
                    composable(Screen.Profile.route) {
                        ProfileScreen(
                            navController = navController,
                            viewModel = viewModel {
                                viewModelFactory.create(ProfileViewModel::class.java)
                            },
                        )
                    }
                    composable(Screen.Competition.route, Screen.Competition.arguments) { backStackEntry ->
                        CompetitionScreen(
                            id = backStackEntry.arguments?.getInt("id") ?: 0,
                            navController = navController,
                            viewModel = viewModel {
                                viewModelFactory.create(CompetitionDetailViewModel::class.java)
                            },
                        )
                    }
                    composable(Screen.SearchResults.route) { }
                    composable(Screen.UserResults.route) { }
                    composable(Screen.CommonResults.route) { }
                    composable(Screen.Settings.route) { SettingsScreen(navController, eruditeTheme) { eruditeTheme = it } }
                    composable(Screen.Info.route) { }
                    composable(Screen.Debug.route) {
                        DebugScreen(
                            navController = navController,
                            viewModel = viewModel {
                                viewModelFactory.create(DebugViewModel::class.java)
                            }
                        )
                    }
                }
                NavigationBarView(navController)
            }
        }
    }
}