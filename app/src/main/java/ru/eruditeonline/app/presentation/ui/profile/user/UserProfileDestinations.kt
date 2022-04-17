package ru.eruditeonline.app.presentation.ui.profile.user

import android.content.Context
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.navigation.Destination
import ru.eruditeonline.app.presentation.ui.profile.ProfileFragmentDirections
import ru.eruditeonline.app.presentation.ui.result.search.SearchResultMode
import ru.eruditeonline.app.presentation.ui.result.user.UserResultParams
import javax.inject.Inject

class UserProfileDestinations @Inject constructor(
    private val context: Context,
) {
    /** Поиск результатов по названию конкурса или учатснику */
    fun searchResultsByQuery() = Destination.Action(
        ProfileFragmentDirections.actionProfileFragmentToSearchResultFragment(SearchResultMode.QUERY)
    )

    /** Результаты пользователя */
    fun userResults() = Destination.Action(
        ProfileFragmentDirections.actionProfileFragmentToUserResultListFragment(UserResultParams.All)
    )

    /** Итоги */
    fun commonResults() = Destination.Action(
        ProfileFragmentDirections.actionProfileFragmentToCommonResultListFragment()
    )

    /** Перезагрузка стека */
    fun reloadStack() = Destination.Stack(
        listOf(
            Destination.DeepLink(
                NavDeepLinkRequest.Builder.fromUri(context.getString(R.string.navigation_deep_link_to_dashboard).toUri()).build(),
                NavOptions.Builder()
                    .setPopUpTo(R.id.nav_graph, true)
                    .build()
            ),
            Destination.DeepLink(
                NavDeepLinkRequest.Builder.fromUri(context.getString(R.string.navigation_deep_link_to_profile).toUri()).build(),
            ),
        )
    )
}