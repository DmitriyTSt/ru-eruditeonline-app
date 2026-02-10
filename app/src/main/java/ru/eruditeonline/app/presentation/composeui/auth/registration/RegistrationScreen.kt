package ru.eruditeonline.app.presentation.composeui.auth.registration

import androidx.compose.runtime.Composable
import ru.eruditeonline.app.presentation.composeui.base.ObserveDestinations
import ru.eruditeonline.app.presentation.composeui.base.appViewModel
import ru.eruditeonline.app.presentation.ui.auth.registration.RegistrationViewModel

@Composable
fun RegistrationScreen(viewModel: RegistrationViewModel = appViewModel()) {
    viewModel.ObserveDestinations()
}