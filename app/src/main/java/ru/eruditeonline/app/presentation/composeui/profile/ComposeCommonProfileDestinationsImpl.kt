package ru.eruditeonline.app.presentation.composeui.profile

import ru.eruditeonline.app.presentation.composeui.dashboard.Dashboard
import ru.eruditeonline.app.presentation.composeui.information.Information
import ru.eruditeonline.app.presentation.composeui.result.common.CommonResults
import ru.eruditeonline.app.presentation.composeui.result.search.SearchResults
import ru.eruditeonline.app.presentation.composeui.result.user.UserResults
import ru.eruditeonline.app.presentation.composeui.settings.Settings
import ru.eruditeonline.app.presentation.navigation.Destination
import ru.eruditeonline.app.presentation.ui.profile.CommonProfileDestinations
import javax.inject.Inject

class ComposeCommonProfileDestinationsImpl @Inject constructor() : CommonProfileDestinations {

    override fun userResults() = Destination.ComposeScreen(UserResults())

    /** Итоги */
    override fun commonResults() = Destination.ComposeScreen(CommonResults)

    /** Поиск результатов по email */
    override fun searchResultsByEmail() = Destination.ComposeScreen(SearchResults)

    /** Перезагрузка стека */
    override fun reloadStack() = Destination.Stack(
        Destination.ComposeScreen(Dashboard),
        Destination.ComposeScreen(Profile),
    )

    /** Информация */
    override fun information() = Destination.ComposeScreen(Information)

    /** Настройки */
    override fun settings() = Destination.ComposeScreen(Settings)
}
