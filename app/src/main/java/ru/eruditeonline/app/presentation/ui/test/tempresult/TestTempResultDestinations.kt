package ru.eruditeonline.app.presentation.ui.test.tempresult

import android.content.Context
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.test.CreatedResult
import ru.eruditeonline.app.presentation.ui.webpage.WebPageFragment
import ru.eruditeonline.navigation.Action
import ru.eruditeonline.navigation.DeepLink
import ru.eruditeonline.navigation.Destination
import ru.eruditeonline.navigation.Destinations
import javax.inject.Inject

class TestTempResultDestinations @Inject constructor(
    private val context: Context,
) : Destinations {
    /** Выбор страны */
    fun selectCountry() = Destination.Action(
        TestTempResultFragmentDirections.actionTestTempResultFragmentToSelectCountryGraph()
    )

    /** Персональные данные */
    fun personalData() = Destination.DeepLink(
        NavDeepLinkRequest.Builder
            .fromUri(
                context.getString(R.string.navigation_deep_link_to_web_page_template, WebPageFragment.PERSONAL_DATA_PATH).toUri()
            )
            .build()
    )

    /** Выбор диплома */
    fun selectDiploma() = Destination.Action(
        TestTempResultFragmentDirections.actionTestTempResultFragmentToSelectDiplomaFragment()
    )

    /** Успешное сохранение результата */
    fun successSaveResult(result: CreatedResult) = Destination.Action(
        TestTempResultFragmentDirections.actionTestTempResultFragmentToSuccessSaveResultFragment(result)
    )
}
