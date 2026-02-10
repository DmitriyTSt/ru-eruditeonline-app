package ru.eruditeonline.app.presentation.composeui.information

import ru.eruditeonline.app.presentation.composeui.webpage.WebPage
import ru.eruditeonline.app.presentation.navigation.Destination
import ru.eruditeonline.app.presentation.ui.information.InformationDestinations
import javax.inject.Inject

class ComposeInformationDestinationsImpl @Inject constructor() : InformationDestinations {
    override fun webPage(path: String) = Destination.ComposeScreen(WebPage(path))
}
