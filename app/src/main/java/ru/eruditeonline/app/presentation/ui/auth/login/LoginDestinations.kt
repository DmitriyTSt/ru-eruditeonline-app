package ru.eruditeonline.app.presentation.ui.auth.login

import android.content.Context
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.ui.webpage.WebPageFragment
import ru.eruditeonline.navigation.Action
import ru.eruditeonline.navigation.DeepLink
import ru.eruditeonline.navigation.Destination
import ru.eruditeonline.navigation.Destinations
import javax.inject.Inject

class LoginDestinations @Inject constructor(
    private val context: Context,
) : Destinations {
    fun registration() = Destination.Action(
        LoginFragmentDirections.actionLoginFragmentToRegistrationFragment()
    )

    fun restorePassword() = Destination.DeepLink(
        NavDeepLinkRequest.Builder
            .fromUri(
                context.getString(
                    R.string.navigation_deep_link_to_web_page_template,
                    WebPageFragment.RESTORE_PASSWORD_PATH,
                ).toUri()
            )
            .build()
    )
}
