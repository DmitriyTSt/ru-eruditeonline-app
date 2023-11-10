package ru.eruditeonline.app.presentation.composeui.profile

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import ru.eruditeonline.app.presentation.composeui.base.ObserveDestinations
import ru.eruditeonline.app.presentation.ui.profile.anonym.AnonymProfileViewModel

@Composable
fun AnonymProfileContent(navController: NavController, viewModel: AnonymProfileViewModel) {
    ObserveDestinations(navController, viewModel)
}