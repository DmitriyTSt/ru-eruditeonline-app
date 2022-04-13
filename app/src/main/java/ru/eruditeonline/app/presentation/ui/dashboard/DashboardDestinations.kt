package ru.eruditeonline.app.presentation.ui.dashboard

import android.content.Context
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.navigation.Destination
import javax.inject.Inject

class DashboardDestinations @Inject constructor(
    private val context: Context,
) {
    /** Конкурс */
    fun competitionItem(id: Int) = Destination.DeepLink(
        NavDeepLinkRequest.Builder
            .fromUri(context.getString(R.string.navigation_deep_link_to_competition_detail_template, id).toUri())
            .build()
    )
}
