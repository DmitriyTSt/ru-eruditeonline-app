package ru.eruditeonline.app.presentation.ui.auth.registration

import android.content.Context
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.navigation.Action
import ru.eruditeonline.app.presentation.navigation.DeepLink
import ru.eruditeonline.app.presentation.ui.webpage.WebPageFragment
import ru.eruditeonline.architecture.presentation.navigation.Destination
import ru.eruditeonline.architecture.presentation.navigation.Destinations
import javax.inject.Inject

class RegistrationDestinations @Inject constructor(
    private val context: Context,
) : Destinations {
    /** Выбор страны */
    fun selectCountry() = Destination.Action(
        RegistrationFragmentDirections.actionRegistrationFragmentToSelectCountryGraph()
    )

    /** Успешная регистрация */
    fun successRegistration() = Destination.Action(
        RegistrationFragmentDirections.actionRegistrationFragmentToRegistrationSuccessFragment()
    )

    /** Персональные данные */
    fun personalData() = Destination.DeepLink(
        NavDeepLinkRequest.Builder
            .fromUri(
                context.getString(R.string.navigation_deep_link_to_web_page_template, WebPageFragment.PERSONAL_DATA_PATH).toUri()
            )
            .build()
    )
}
