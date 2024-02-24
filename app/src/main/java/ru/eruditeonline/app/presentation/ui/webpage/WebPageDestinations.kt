package ru.eruditeonline.app.presentation.ui.webpage

import android.content.Intent
import android.net.Uri
import ru.eruditeonline.architecture.presentation.navigation.Destination
import ru.eruditeonline.architecture.presentation.navigation.Destinations
import javax.inject.Inject

class WebPageDestinations @Inject constructor() : Destinations {
    /** Внешний браузер */
    fun browser(uri: Uri) = Destination.Activity(Intent(Intent.ACTION_VIEW, uri))
}
