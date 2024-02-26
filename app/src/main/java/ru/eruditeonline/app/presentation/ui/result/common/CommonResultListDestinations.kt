package ru.eruditeonline.app.presentation.ui.result.common

import android.content.Context
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import ru.eruditeonline.app.R
import ru.eruditeonline.navigation.DeepLink
import ru.eruditeonline.navigation.Destination
import ru.eruditeonline.navigation.Destinations
import javax.inject.Inject

class CommonResultListDestinations @Inject constructor(
    private val context: Context,
) : Destinations {

    /** Конкурс */
    fun competitionItem(id: Int) = Destination.DeepLink(
        NavDeepLinkRequest.Builder
            .fromUri(context.getString(R.string.navigation_deep_link_to_competition_detail_template, id).toUri())
            .build()
    )
}
