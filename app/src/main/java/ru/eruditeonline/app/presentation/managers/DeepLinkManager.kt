package ru.eruditeonline.app.presentation.managers

import android.content.Context
import android.net.Uri
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.navigation.Destination
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeepLinkManager @Inject constructor(
    private val context: Context,
    private val destinations: DeepLinkDestinations,
) {
    private val registrationPath by lazy { context.getString(R.string.deep_link_registration_path) }
    private val confirmEmailTokenQueryName by lazy { context.getString(R.string.deep_link_confirm_email_token_query_name) }

    private var deepLink: Uri? = null

    fun setDeepLink(uri: Uri?) {
        deepLink = uri
    }

    fun resolveDeepLink(): Destination? {
        val deepLink = this.deepLink ?: return null

        val destination = when (deepLink.path) {
            registrationPath -> {
                val token = deepLink.getQueryParameter(confirmEmailTokenQueryName)
                if (token.isNullOrEmpty()) {
                    destinations.registration()
                } else {
                    destinations.confirmEmail(token)
                }
            }
            else -> null
        }
        this.deepLink = null
        return destination
    }
}