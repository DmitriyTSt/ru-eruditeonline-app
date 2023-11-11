package ru.eruditeonline.app.presentation.composeui.profile

import ru.eruditeonline.app.presentation.composeui.model.Screen
import ru.eruditeonline.app.presentation.navigation.Destination
import ru.eruditeonline.app.presentation.ui.profile.CommonProfileDestinations
import javax.inject.Inject

class ComposeCommonProfileDestinationsImpl @Inject constructor() : CommonProfileDestinations {

    override fun userResults() = Destination.ComposeScreen(Screen.UserResults.route)

    /** Итоги */
    override fun commonResults() = Destination.ComposeScreen(Screen.CommonResults.route)

    /** Поиск результатов по email */
    override fun searchResultsByEmail() = Destination.ComposeScreen(Screen.SearchResults.route)

    /** Перезагрузка стека */
    override fun reloadStack() = Destination.Stack(
        Destination.ComposeScreen(Screen.Dashboard.route),
        Destination.ComposeScreen(Screen.Profile.route),
    )

    /** Информация */
    override fun information() = Destination.ComposeScreen(Screen.Info.route)

    /** Настройки */
    override fun settings() = Destination.ComposeScreen(Screen.Settings.route)
}
