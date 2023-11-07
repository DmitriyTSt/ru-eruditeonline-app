package ru.eruditeonline.app.presentation.ui.dashboard

import android.content.Intent
import android.net.Uri
import ru.eruditeonline.app.presentation.navigation.Destination

interface DashboardDestinations {
    /** Конкурс */
    fun competitionItem(id: Int): Destination

    /** Внешний браузер */
    fun browser(uri: Uri) = Destination.Activity(Intent(Intent.ACTION_VIEW, uri))

    /** Веб-страница */
    fun webPage(path: String): Destination

    /** Дебаг */
    fun debug(): Destination
}
