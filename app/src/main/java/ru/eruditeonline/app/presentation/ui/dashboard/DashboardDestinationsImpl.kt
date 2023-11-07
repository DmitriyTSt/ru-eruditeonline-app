package ru.eruditeonline.app.presentation.ui.dashboard

import android.content.Context
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.navigation.Destination
import javax.inject.Inject

class DashboardDestinationsImpl @Inject constructor(
    private val context: Context,
) : DashboardDestinations {
    /** Конкурс */
    override fun competitionItem(id: Int) = Destination.DeepLink(
        NavDeepLinkRequest.Builder
            .fromUri(context.getString(R.string.navigation_deep_link_to_competition_detail_template, id).toUri())
            .build()
    )

    /** Веб-страница */
    override fun webPage(path: String) = Destination.DeepLink(
        NavDeepLinkRequest.Builder
            .fromUri(context.getString(R.string.navigation_deep_link_to_web_page_template, path).toUri())
            .build()
    )

    /** Дебаг */
    override fun debug() = Destination.DeepLink(
        NavDeepLinkRequest.Builder
            .fromUri(context.getString(R.string.navigation_deep_link_to_debug).toUri())
            .build()
    )
}
