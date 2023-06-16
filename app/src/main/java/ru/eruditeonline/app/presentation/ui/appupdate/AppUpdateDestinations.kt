package ru.eruditeonline.app.presentation.ui.appupdate

import android.content.Context
import android.content.Intent
import android.net.Uri
import ru.eruditeonline.app.presentation.navigation.Destination
import javax.inject.Inject

class AppUpdateDestinations @Inject constructor(
    private val context: Context,
) {
    fun googlePlayAppPage() = Destination.Activity(
        Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("http://play.google.com/store/apps/details?id=${context.packageName}")
        }
    )

    fun main() = Destination.Action(
        AppUpdateFragmentDirections.actionAppUpdateFragmentToDashboardGraph()
    )
}
