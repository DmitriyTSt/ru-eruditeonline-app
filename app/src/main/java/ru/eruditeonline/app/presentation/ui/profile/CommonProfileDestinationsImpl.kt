package ru.eruditeonline.app.presentation.ui.profile

import android.content.Context
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.navigation.Destination
import ru.eruditeonline.app.presentation.ui.result.user.UserResultParams
import javax.inject.Inject

class CommonProfileDestinationsImpl @Inject constructor(
    private val context: Context,
) : CommonProfileDestinations {

    /** Результаты пользователя */
    override fun userResults() = Destination.Action(
        ProfileFragmentDirections.actionProfileFragmentToUserResultListFragment(UserResultParams.All)
    )

    /** Итоги */
    override fun commonResults() = Destination.Action(
        ProfileFragmentDirections.actionProfileFragmentToCommonResultListFragment()
    )

    /** Поиск результатов по email */
    override fun searchResultsByEmail() = Destination.Action(
        ProfileFragmentDirections.actionProfileFragmentToSearchResultFragment()
    )

    /** Перезагрузка стека */
    override fun reloadStack() = Destination.Stack(
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
    override fun information() = Destination.Action(
        ProfileFragmentDirections.actionProfileFragmentToInformationFragment()
    )

    /** Настройки */
    override fun settings() = Destination.Action(
        ProfileFragmentDirections.actionProfileFragmentToSettingsFragment()
    )
}
