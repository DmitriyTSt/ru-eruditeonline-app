package ru.eruditeonline.app.presentation.managers

import android.content.Context
import androidx.annotation.StringRes
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.navigation.Destination
import ru.eruditeonline.app.presentation.ui.rating.tab.RatingTabItemMode
import ru.eruditeonline.app.presentation.ui.result.search.SearchResultMode
import javax.inject.Inject

class DeepLinkDestinations @Inject constructor(
    private val context: Context,
) {
    fun registration() = Destination.DeepLink(
        simpleCreate(R.string.navigation_deep_link_to_registration)
    )

    fun confirmEmail(token: String) = Destination.DeepLink(
        NavDeepLinkRequest.Builder
            .fromUri(context.getString(R.string.navigation_deep_link_to_confirm_email_template, token).toUri())
            .build()
    )

    fun login() = Destination.DeepLink(
        simpleCreate(R.string.navigation_deep_link_to_login)
    )

    fun profile() = Destination.DeepLink(
        simpleCreate(R.string.navigation_deep_link_to_profile)
    )

    fun commonResults() = Destination.DeepLink(
        simpleCreate(R.string.navigation_deep_link_to_results)
    )

    fun competition(id: Int) = Destination.DeepLink(
        NavDeepLinkRequest.Builder
            .fromUri(context.getString(R.string.navigation_deep_link_to_competition_detail_template, id).toUri())
            .build()
    )

    fun test(id: String) = Destination.DeepLink(
        NavDeepLinkRequest.Builder
            .fromUri(context.getString(R.string.navigation_deep_link_to_test_passage_template, id).toUri())
            .build()
    )

    fun rating(mode: RatingTabItemMode) = Destination.DeepLink(
        NavDeepLinkRequest.Builder
            .fromUri(context.getString(R.string.navigation_deep_link_to_rating_single_tab_template, mode).toUri())
            .build()
    )

    fun searchResultsByEmail() = Destination.DeepLink(
        NavDeepLinkRequest.Builder
            .fromUri(context.getString(R.string.navigation_deep_link_to_results_search_template, SearchResultMode.EMAIL).toUri())
            .build()
    )

    fun competitionItems() = Destination.DeepLink(
        simpleCreate(R.string.navigation_deep_link_to_competitions)
    )

    private fun simpleCreate(@StringRes deepLinkRes: Int): NavDeepLinkRequest {
        return NavDeepLinkRequest.Builder
            .fromUri(context.getString(deepLinkRes).toUri())
            .build()
    }
}
