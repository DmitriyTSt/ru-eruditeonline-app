package ru.eruditeonline.app.presentation.ui.test.successresult

import android.content.Context
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.navigation.Destination
import javax.inject.Inject

class SuccessSaveResultDestinations @Inject constructor(
    private val context: Context,
) {
    /** Результат */
    fun result(id: Int) = Destination.Action(
        SuccessSaveResultFragmentDirections.actionSuccessSaveResultFragmentToResultDetailGraph(id)
    )

    /** Конкурсы */
    fun competitions() = Destination.DeepLink(
        NavDeepLinkRequest.Builder
            .fromUri(context.getString(R.string.navigation_deep_link_to_competitions).toUri())
            .build(),
        NavOptions.Builder()
            .setPopUpTo(R.id.competition_detail_graph, true)
            .build()
    )
}
