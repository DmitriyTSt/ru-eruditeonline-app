package ru.eruditeonline.app.presentation.composeui.mainacitivty

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.composeui.appupdate.AppUpdate
import ru.eruditeonline.app.presentation.composeui.appupdate.AppUpdateScreen
import ru.eruditeonline.app.presentation.composeui.auth.login.Login
import ru.eruditeonline.app.presentation.composeui.auth.login.LoginScreen
import ru.eruditeonline.app.presentation.composeui.auth.registration.Registration
import ru.eruditeonline.app.presentation.composeui.auth.registration.RegistrationScreen
import ru.eruditeonline.app.presentation.composeui.base.BaseScreen
import ru.eruditeonline.app.presentation.composeui.base.LocalBackStack
import ru.eruditeonline.app.presentation.composeui.base.LocalBottomNavigationPadding
import ru.eruditeonline.app.presentation.composeui.base.LocalScreenResultDispatcher
import ru.eruditeonline.app.presentation.composeui.base.LocalViewModelFactory
import ru.eruditeonline.app.presentation.composeui.base.ScreenWithBottomNavigation
import ru.eruditeonline.app.presentation.composeui.base.rememberScreenResultDispatcher
import ru.eruditeonline.app.presentation.composeui.competition.detail.Competition
import ru.eruditeonline.app.presentation.composeui.competition.detail.CompetitionScreen
import ru.eruditeonline.app.presentation.composeui.competition.filter.CompetitionFilter
import ru.eruditeonline.app.presentation.composeui.competition.filter.CompetitionFilterScreen
import ru.eruditeonline.app.presentation.composeui.competition.items.Competitions
import ru.eruditeonline.app.presentation.composeui.competition.items.CompetitionsScreen
import ru.eruditeonline.app.presentation.composeui.dashboard.Dashboard
import ru.eruditeonline.app.presentation.composeui.dashboard.DashboardScreen
import ru.eruditeonline.app.presentation.composeui.debug.Debug
import ru.eruditeonline.app.presentation.composeui.debug.DebugScreen
import ru.eruditeonline.app.presentation.composeui.profile.Profile
import ru.eruditeonline.app.presentation.composeui.profile.ProfileScreen
import ru.eruditeonline.app.presentation.composeui.rating.Rating
import ru.eruditeonline.app.presentation.composeui.rating.RatingScreen
import ru.eruditeonline.app.presentation.composeui.result.common.CommonResultListScreen
import ru.eruditeonline.app.presentation.composeui.result.common.CommonResults
import ru.eruditeonline.app.presentation.composeui.webpage.WebPage
import ru.eruditeonline.app.presentation.composeui.webpage.InfoScreen
import ru.eruditeonline.app.presentation.composeui.result.search.SearchResults
import ru.eruditeonline.app.presentation.composeui.result.search.SearchResultsScreen
import ru.eruditeonline.app.presentation.composeui.result.user.UserResults
import ru.eruditeonline.app.presentation.composeui.result.user.UserResultsScreen
import ru.eruditeonline.app.presentation.composeui.settings.Settings
import ru.eruditeonline.app.presentation.composeui.settings.SettingsScreen
import ru.eruditeonline.app.presentation.composeui.splash.Splash
import ru.eruditeonline.app.presentation.composeui.splash.SplashScreen
import ru.eruditeonline.app.presentation.composeui.theme.EruditeTheme
import ru.eruditeonline.app.presentation.composeui.theme.EruditeThemeModel

@Composable
fun EruditeComposeApp(startScreen: BaseScreen, viewModelFactory: ViewModelProvider.Factory) {
    var eruditeTheme by remember { mutableStateOf(EruditeThemeModel.STANDARD_LIGHT) }
    val backStack = remember { mutableStateListOf(startScreen) }
    val screenResultDispatcher = rememberScreenResultDispatcher()
    val hazeState = rememberHazeState()
    var bottomNavigationHeight by remember { mutableIntStateOf(0) }
    val density = LocalDensity.current
    val bottomNavigationPadding = remember(bottomNavigationHeight) {
        if (bottomNavigationHeight > 0) {
            with(density) { bottomNavigationHeight.toDp() } + 16.dp
        } else {
            0.dp
        }
    }

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
                    LocalBottomNavigationPadding provides bottomNavigationPadding,
                    LocalScreenResultDispatcher provides screenResultDispatcher,
                ) {
                    NavDisplay(
                        backStack = backStack,
                        onBack = { backStack.removeLastOrNull() },
                        modifier = Modifier
                            .fillMaxSize()
                            .hazeSource(hazeState),
                        entryProvider = entryProvider {
                            entry<Splash> { SplashScreen() }
                            entry<AppUpdate> { AppUpdateScreen() }
                            entry<Dashboard> { DashboardScreen() }
                            entry<Competitions> { CompetitionsScreen() }
                            entry<CompetitionFilter> { CompetitionFilterScreen(it.filters) }
                            entry<Rating> { RatingScreen() }
                            entry<Profile> { ProfileScreen() }
                            entry<Competition> { CompetitionScreen(it.id) }
                            entry<SearchResults> { SearchResultsScreen() }
                            entry<UserResults> { UserResultsScreen(initialEmail = it.email) }
                            entry<CommonResults> { CommonResultListScreen() }
                            entry<Settings> {
                                SettingsScreen(
                                    currentTheme = eruditeTheme,
                                    onBackClick = { backStack.removeLastOrNull() },
                                    selectTheme = { eruditeTheme = it },
                                )
                            }
                            entry<WebPage> { InfoScreen(it.path) }
                            entry<Debug> { DebugScreen() }
                            entry<Login> { LoginScreen() }
                            entry<Registration> { RegistrationScreen() }
                        },
                    )
                }

                val density = LocalDensity.current
                val horizontalPadding = 24.dp
                val bottomPadding = dimensionResource(id = R.dimen.bottom_navigation_view_margin_bottom)
                val currentScreen = backStack.lastOrNull()

                AnimatedVisibility(
                    visible = currentScreen is ScreenWithBottomNavigation,
                    modifier = Modifier.align(Alignment.BottomCenter),
                    enter = slideInVertically { it },
                    exit = slideOutVertically { it },
                ) {
                    if (currentScreen != null) {
                        NavigationBarView(
                            currentScreen = currentScreen,
                            backStack = backStack,
                            hazeState = hazeState,
                            modifier = Modifier
                                .padding(horizontal = horizontalPadding)
                                .padding(bottom = bottomPadding + with(density) {
                                    WindowInsets.navigationBars.getBottom(density).toDp()
                                }),
                        ) {
                            bottomNavigationHeight = it.height
                        }
                    }
                }
                if (currentScreen !is ScreenWithBottomNavigation) {
                    bottomNavigationHeight = 0
                }
            }
        }
    }
}