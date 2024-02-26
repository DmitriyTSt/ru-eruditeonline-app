package ru.eruditeonline.app.presentation.ui.webpage

import android.content.Intent
import android.net.Uri
import ru.eruditeonline.navigation.Activity
import ru.eruditeonline.navigation.Destination
import ru.eruditeonline.navigation.Destinations
import javax.inject.Inject

class WebPageDestinations @Inject constructor() : Destinations {
    /** Внешний браузер */
    fun browser(uri: Uri) = Destination.Activity(Intent(Intent.ACTION_VIEW, uri))
}
