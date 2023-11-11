package ru.eruditeonline.app.presentation.ui.profile.anonym

import ru.eruditeonline.app.presentation.navigation.Destination

interface AnonymProfileDestinations {
    /** Вход */
    fun login(): Destination

    /** Регистрация */
    fun registration(): Destination
}
