package ru.eruditeonline.app.presentation.ui.webpage

import android.content.Intent
import android.net.Uri
import ru.eruditeonline.app.presentation.navigation.Destination
import javax.inject.Inject

class WebPageDestinations @Inject constructor() {
    /** Внешний браузер */
    fun browser(uri: Uri) = Destination.Activity(Intent(Intent.ACTION_VIEW, uri))
}
