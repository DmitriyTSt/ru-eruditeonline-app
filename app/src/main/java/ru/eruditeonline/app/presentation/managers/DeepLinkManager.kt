package ru.eruditeonline.app.presentation.managers

import android.content.Context
import android.net.Uri
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.navigation.Destination
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeepLinkManager @Inject constructor(
    private val context: Context,
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
            DeepLink.REGISTRATION -> {
                val token = deepLink.getQueryParameter(DeepLink.CONFIRM_EMAIL_QUERY_NAME)
                if (token.isNullOrEmpty()) {
                    destinations.registration()
                } else {
                    destinations.confirmEmail(token)
                }
            }
            DeepLink.LOGIN -> destinations.login()
            DeepLink.PROFILE -> destinations.profile()
            DeepLink.COMMON_RESULTS -> destinations.commonResults()
            DeepLink.TEST -> {
                val testId = deepLink.getQueryParameter(DeepLink.TEST_QUERY_NAME)
                if (testId.isNullOrEmpty()) {
                    null
                } else {
                    destinations.test(testId)
                }
            }
            else -> {
                when {
                    deepLink.path?.startsWith(DeepLink.COMPETITION_PREFIX) == true -> {
                        val competitionId = deepLink.path.orEmpty()
                            .replace(DeepLink.COMPETITION_PREFIX, "")
                            .replace(".html", "")
                            .toIntOrNull()
                        if (competitionId != null) {
                            destinations.competition(competitionId)
                        } else {
                            null
                        }
                    }
                    else -> null
                }
            }
        }
    }
}
