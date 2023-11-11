package ru.eruditeonline.app.presentation.composeui.profile

import ru.eruditeonline.app.presentation.composeui.model.Screen
import ru.eruditeonline.app.presentation.navigation.Destination
import ru.eruditeonline.app.presentation.ui.profile.anonym.AnonymProfileDestinations
import javax.inject.Inject

class ComposeAnonymProfileDestinationsImpl @Inject constructor() : AnonymProfileDestinations {

    /** Вход */
    override fun login() = Destination.ComposeScreen(Screen.Login.route)

    /** Регистрация */
    override fun registration() = Destination.ComposeScreen(Screen.Registration.route)
}
