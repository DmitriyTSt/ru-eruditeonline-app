package ru.eruditeonline.app.presentation.composeui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.composeui.base.BaseScreen
import ru.eruditeonline.app.presentation.composeui.competition.items.Competitions as CompetitionsScreen
import ru.eruditeonline.app.presentation.composeui.dashboard.Dashboard as DashboardScreen
import ru.eruditeonline.app.presentation.composeui.profile.Profile as ProfileScreen
import ru.eruditeonline.app.presentation.composeui.rating.Rating as RatingScreen

sealed class BottomMenuItem(val screen: BaseScreen, @DrawableRes val iconRes: Int, @StringRes val titleRes: Int) {
    data object Dashboard :
        BottomMenuItem(DashboardScreen, R.drawable.ic_menu_dashboard_inactive, R.string.dashboard_menu_label)

    data object Competitions :
        BottomMenuItem(CompetitionsScreen, R.drawable.ic_menu_competitions_inactive, R.string.competition_menu_label)

    data object Rating : BottomMenuItem(RatingScreen, R.drawable.ic_menu_rating_inactive, R.string.rating_menu_label)
    data object Profile : BottomMenuItem(ProfileScreen, R.drawable.ic_menu_profile_inactive, R.string.profile_menu_label)
}