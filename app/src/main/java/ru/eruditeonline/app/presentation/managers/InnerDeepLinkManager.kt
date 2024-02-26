package ru.eruditeonline.app.presentation.managers

import android.net.Uri
import ru.eruditeonline.navigation.Destination

interface InnerDeepLinkManager {
    fun resolveDeepLink(uri: Uri): Destination?
}
