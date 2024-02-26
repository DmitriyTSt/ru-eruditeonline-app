package ru.eruditeonline.app.presentation.ui.profile.user

import android.content.Context
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.ui.profile.ProfileFragmentDirections
import ru.eruditeonline.app.presentation.ui.result.user.UserResultParams
import ru.eruditeonline.navigation.Action
import ru.eruditeonline.navigation.DeepLink
import ru.eruditeonline.navigation.Destination
import ru.eruditeonline.navigation.Destinations
import javax.inject.Inject

class UserProfileDestinations @Inject constructor(
    private val context: Context,
) : Destinations {
    /** Поиск результатов по email */
    fun searchResultsByEmail() = Destination.Action(
        ProfileFragmentDirections.actionProfileFragmentToSearchResultFragment()
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

    /** Информация */
    fun information() = Destination.Action(
        ProfileFragmentDirections.actionProfileFragmentToInformationFragment()
    )

    /** Настройки */
    fun settings() = Destination.Action(
        ProfileFragmentDirections.actionProfileFragmentToSettingsFragment()
    )
}
