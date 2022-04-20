package ru.eruditeonline.app.presentation.managers

import android.content.Context
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.navigation.Destination
import javax.inject.Inject

class DeepLinkDestinations @Inject constructor(
    private val context: Context,
) {
    fun registration() = Destination.DeepLink(
        NavDeepLinkRequest.Builder
            .fromUri(context.getString(R.string.navigation_deep_link_to_registration).toUri())
            .build()
    )

    fun confirmEmail(token: String) = Destination.DeepLink(
        NavDeepLinkRequest.Builder
            .fromUri(context.getString(R.string.navigation_deep_link_to_confirm_email_template, token).toUri())
            .build()
    )
}
