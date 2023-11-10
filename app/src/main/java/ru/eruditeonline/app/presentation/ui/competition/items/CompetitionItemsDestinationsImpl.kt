package ru.eruditeonline.app.presentation.ui.competition.items

import android.content.Context
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.competition.CompetitionFilters
import ru.eruditeonline.app.presentation.navigation.Destination
import javax.inject.Inject

class CompetitionItemsDestinationsImpl @Inject constructor(
    private val context: Context,
) : CompetitionItemsDestinations {
    /** Фильтр */
    override fun filter(filter: CompetitionFilters) = Destination.Action(
        CompetitionItemsFragmentDirections.actionCompetitionItemsFragmentToCompetitionFilterFragment(filter)
    )

    /** Конкурс */
    override fun competitionItem(id: Int) = Destination.DeepLink(
        NavDeepLinkRequest.Builder
            .fromUri(context.getString(R.string.navigation_deep_link_to_competition_detail_template, id).toUri())
            .build()
    )
}
