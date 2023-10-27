package ru.eruditeonline.app.presentation.composeui.model

import androidx.annotation.DrawableRes
import ru.eruditeonline.app.R

sealed class BottomMenuItem(val route: String, @DrawableRes val icon: Int) {
    data object Dashboard : BottomMenuItem(Screen.Dashboard.route, R.drawable.ic_menu_dashboard_inactive)
    data object Competitions : BottomMenuItem(Screen.Competitions.route, R.drawable.ic_menu_competitions_inactive)
    data object Rating : BottomMenuItem(Screen.Rating.route, R.drawable.ic_menu_rating_inactive)
    data object Profile : BottomMenuItem(Screen.Profile.route, R.drawable.ic_menu_profile_inactive)
}