package ru.eruditeonline.app.presentation.ui.information

import ru.eruditeonline.app.presentation.navigation.Destination

interface InformationDestinations {
    fun webPage(path: String): Destination
}
