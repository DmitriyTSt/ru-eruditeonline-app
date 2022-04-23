package ru.eruditeonline.app.presentation.managers

import android.net.Uri
import ru.eruditeonline.app.presentation.navigation.Destination
import ru.eruditeonline.app.presentation.ui.rating.tab.RatingTabItemMode
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeepLinkManager @Inject constructor(
    private val destinations: DeepLinkDestinations,
) : InnerDeepLinkManager {

    private var deepLink: Uri? = null

    fun setDeepLink(uri: Uri?) {
        deepLink = uri
    }

    fun resolveDeepLink(): Destination? {
        val deepLink = this.deepLink ?: return null

        val destination = resolveDeepLinkDestination(deepLink)
        this.deepLink = null
        return destination
    }

    override fun resolveDeepLink(uri: Uri): Destination? {
        return resolveDeepLinkDestination(uri)
    }

    private fun resolveDeepLinkDestination(deepLink: Uri): Destination? {
        return when (deepLink.path) {
            DeepLink.Auth.REGISTRATION -> {
                val token = deepLink.getQueryParameter(DeepLink.Auth.CONFIRM_EMAIL_QUERY_NAME)
                if (token.isNullOrEmpty()) {
                    destinations.registration()
                } else {
                    destinations.confirmEmail(token)
                }
            }
            DeepLink.Auth.LOGIN -> destinations.login()
            DeepLink.Profile.PROFILE -> destinations.profile()
            DeepLink.Profile.COMMON_RESULTS -> destinations.commonResults()
            DeepLink.Competition.TEST -> {
                val testId = deepLink.getQueryParameter(DeepLink.Competition.TEST_QUERY_NAME)
                if (testId.isNullOrEmpty()) {
                    null
                } else {
                    destinations.test(testId)
                }
            }
            DeepLink.Rating.DAY -> destinations.rating(RatingTabItemMode.DAY)
            DeepLink.Rating.MONTH -> destinations.rating(RatingTabItemMode.MONTH)
            DeepLink.Rating.YEAR -> destinations.rating(RatingTabItemMode.YEAR)
            DeepLink.Profile.SEARCH_RESULTS_BY_EMAIL -> destinations.searchResultsByEmail()
            DeepLink.CompetitionList.ITEMS -> destinations.competitionItems()
            DeepLink.Profile.PERSONAL_DATA -> destinations.webPage(deepLink.path.orEmpty())
            else -> {
                when {
                    deepLink.path?.startsWith(DeepLink.Competition.PREFIX) == true -> {
                        val competitionId = deepLink.path.orEmpty()
                            .replace(DeepLink.Competition.PREFIX, "")
                            .replace(".html", "")
                            .toIntOrNull()
                        if (competitionId != null) {
                            destinations.competition(competitionId)
                        } else {
                            null
                        }
                    }
                    deepLink.path?.startsWith(DeepLink.CompetitionList.FILTER_PREFIX) == true -> {
                        destinations.competitionItems()
                    }
                    else -> null
                }
            }
        }
    }
}
