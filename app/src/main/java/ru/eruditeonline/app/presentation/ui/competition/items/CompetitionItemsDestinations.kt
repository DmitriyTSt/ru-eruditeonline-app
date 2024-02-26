package ru.eruditeonline.app.presentation.ui.competition.items

import android.content.Context
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.competition.CompetitionFilters
import ru.eruditeonline.navigation.Action
import ru.eruditeonline.navigation.DeepLink
import ru.eruditeonline.navigation.Destination
import ru.eruditeonline.navigation.Destinations
import javax.inject.Inject

class CompetitionItemsDestinations @Inject constructor(
    private val context: Context,
) : Destinations {
    /** Фильтр */
    fun filter(filter: CompetitionFilters) = Destination.Action(
        CompetitionItemsFragmentDirections.actionCompetitionItemsFragmentToCompetitionFilterFragment(filter)
    )

    /** Конкурс */
    fun competitionItem(id: Int) = Destination.DeepLink(
        NavDeepLinkRequest.Builder
            .fromUri(context.getString(R.string.navigation_deep_link_to_competition_detail_template, id).toUri())
            .build()
    )
}
