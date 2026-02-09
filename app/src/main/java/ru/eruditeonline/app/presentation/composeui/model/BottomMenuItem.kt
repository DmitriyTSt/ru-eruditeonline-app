package ru.eruditeonline.app.presentation.composeui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.eruditeonline.app.R

sealed class BottomMenuItem(val route: String, @DrawableRes val iconRes: Int, @StringRes val titleRes: Int) {
    data object Dashboard :
        BottomMenuItem(Screen.Dashboard.route, R.drawable.ic_menu_dashboard_inactive, R.string.dashboard_menu_label)

    data object Competitions :
        BottomMenuItem(Screen.Competitions.route, R.drawable.ic_menu_competitions_inactive, R.string.competition_menu_label)

    data object Rating : BottomMenuItem(Screen.Rating.route, R.drawable.ic_menu_rating_inactive, R.string.rating_menu_label)
    data object Profile : BottomMenuItem(Screen.Profile.route, R.drawable.ic_menu_profile_inactive, R.string.profile_menu_label)
}