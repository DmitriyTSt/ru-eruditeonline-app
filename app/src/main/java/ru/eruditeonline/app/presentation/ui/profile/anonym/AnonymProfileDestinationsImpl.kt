package ru.eruditeonline.app.presentation.ui.profile.anonym

import android.content.Context
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.navigation.Destination
import ru.eruditeonline.app.presentation.ui.profile.ProfileFragmentDirections
import javax.inject.Inject

class AnonymProfileDestinationsImpl @Inject constructor(
    private val context: Context,
) : AnonymProfileDestinations {
    /** Вход */
    override fun login() = Destination.Action(
        ProfileFragmentDirections.actionProfileFragmentToAuthGraph()
    )

    /** Регистрация */
    override fun registration() = Destination.DeepLink(
        NavDeepLinkRequest.Builder
            .fromUri(context.getString(R.string.navigation_deep_link_to_registration).toUri())
            .build()
    )
}
