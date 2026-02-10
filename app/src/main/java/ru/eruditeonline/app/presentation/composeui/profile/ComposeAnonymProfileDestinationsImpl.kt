package ru.eruditeonline.app.presentation.composeui.profile

import ru.eruditeonline.app.presentation.composeui.auth.login.Login
import ru.eruditeonline.app.presentation.composeui.auth.registration.Registration
import ru.eruditeonline.app.presentation.navigation.Destination
import ru.eruditeonline.app.presentation.ui.profile.anonym.AnonymProfileDestinations
import javax.inject.Inject

class ComposeAnonymProfileDestinationsImpl @Inject constructor() : AnonymProfileDestinations {

    /** Вход */
    override fun login() = Destination.ComposeScreen(Login)

    /** Регистрация */
    override fun registration() = Destination.ComposeScreen(Registration)
}
