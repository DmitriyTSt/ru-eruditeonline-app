package ru.eruditeonline.app.presentation.ui.profile.anonym

import android.content.Context
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.navigation.Destination
import ru.eruditeonline.app.presentation.ui.profile.ProfileFragmentDirections
import ru.eruditeonline.app.presentation.ui.result.search.SearchResultMode
import javax.inject.Inject

class AnonymProfileDestinations @Inject constructor(
    private val context: Context,
) {
    /** Вход */
    fun login() = Destination.Action(
        ProfileFragmentDirections.actionProfileFragmentToAuthGraph()
    )

    /** Регистрация */
    fun registration() = Destination.DeepLink(
        NavDeepLinkRequest.Builder
            .fromUri(context.getString(R.string.navigation_deep_link_to_registration).toUri())
            .build()
    )

    /** Итоги */
    fun commonResults() = Destination.Action(
        ProfileFragmentDirections.actionProfileFragmentToCommonResultListFragment()
    )

    /** Поиск результатов по email */
    fun searchResultsByEmail() = Destination.Action(
        ProfileFragmentDirections.actionProfileFragmentToSearchResultFragment(SearchResultMode.EMAIL)
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

    /** Информация */
    fun information() = Destination.Action(
        ProfileFragmentDirections.actionProfileFragmentToInformationFragment()
    )
}
