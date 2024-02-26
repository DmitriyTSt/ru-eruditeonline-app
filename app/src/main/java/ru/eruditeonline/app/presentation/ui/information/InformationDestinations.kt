package ru.eruditeonline.app.presentation.ui.information

import ru.eruditeonline.navigation.Action
import ru.eruditeonline.navigation.Destination
import ru.eruditeonline.navigation.Destinations
import javax.inject.Inject

class InformationDestinations @Inject constructor() : Destinations {
    fun webPage(path: String) = Destination.Action(
        InformationFragmentDirections.actionInformationFragmentToWebPageFragment(path)
    )
}
