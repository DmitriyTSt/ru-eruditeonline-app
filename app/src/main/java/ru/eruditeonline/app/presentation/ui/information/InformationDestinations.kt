package ru.eruditeonline.app.presentation.ui.information

import ru.eruditeonline.app.presentation.navigation.Destination
import javax.inject.Inject

class InformationDestinations @Inject constructor() {
    fun webPage(path: String) = Destination.Action(
        InformationFragmentDirections.actionInformationFragmentToWebPageFragment(path)
    )
}