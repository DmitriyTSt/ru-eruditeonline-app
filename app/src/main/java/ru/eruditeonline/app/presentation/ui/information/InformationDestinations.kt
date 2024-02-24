package ru.eruditeonline.app.presentation.ui.information

import ru.eruditeonline.app.presentation.navigation.Action
import ru.eruditeonline.architecture.presentation.navigation.Destination
import ru.eruditeonline.architecture.presentation.navigation.Destinations
import javax.inject.Inject

class InformationDestinations @Inject constructor() : Destinations {
    fun webPage(path: String) = Destination.Action(
        InformationFragmentDirections.actionInformationFragmentToWebPageFragment(path)
    )
}
