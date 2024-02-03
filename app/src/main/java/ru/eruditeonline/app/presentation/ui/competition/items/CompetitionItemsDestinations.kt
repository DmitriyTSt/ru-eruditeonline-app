package ru.eruditeonline.app.presentation.ui.competition.items

import android.content.Context
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.FragmentNavigatorExtras
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.competition.CompetitionFilters
import ru.eruditeonline.app.data.model.competition.CompetitionItemShort
import ru.eruditeonline.app.presentation.navigation.Destination
import javax.inject.Inject

class CompetitionItemsDestinations @Inject constructor(
    private val context: Context,
) {
    /** Фильтр */
    fun filter(filter: CompetitionFilters) = Destination.Action(
        CompetitionItemsFragmentDirections.actionCompetitionItemsFragmentToCompetitionFilterFragment(filter)
    )

    /** Конкурс */
    fun competitionItem(item: CompetitionItemShort, imageView: ImageView) = Destination.DeepLink(
        navDeepLinkRequest = NavDeepLinkRequest.Builder
            .fromUri(
                context.getString(
                    R.string.navigation_deep_link_to_competition_detail_with_transition_template,
                    item.id,
                    item.transitionName,
                    item.icon,
                ).toUri()
            )
            .build(),
        extras = FragmentNavigatorExtras(imageView to item.transitionName),
    )
}
