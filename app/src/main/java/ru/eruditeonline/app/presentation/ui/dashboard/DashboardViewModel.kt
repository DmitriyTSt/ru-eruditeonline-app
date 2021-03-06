package ru.eruditeonline.app.presentation.ui.dashboard

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.data.model.competition.CompetitionItemShort
import ru.eruditeonline.app.data.model.main.MainSection
import ru.eruditeonline.app.data.model.main.Tagline
import ru.eruditeonline.app.domain.usecase.debug.IsDebugButtonVisibleUseCase
import ru.eruditeonline.app.domain.usecase.main.GetMainSectionsUseCase
import ru.eruditeonline.app.presentation.managers.DeepLinkManager
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import ru.eruditeonline.app.presentation.ui.views.ScrollStateHolder
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val getMainSectionsUseCase: GetMainSectionsUseCase,
    private val destinations: DashboardDestinations,
    private val deepLinkManager: DeepLinkManager,
    private val isDebugButtonVisibleUseCase: IsDebugButtonVisibleUseCase,
) : BaseViewModel() {
    /** Блоки главной страницы */
    private val _mainSectionsLiveData = MutableLiveData<LoadableState<List<MainSection>>>()
    val mainSectionsLiveData: LiveData<LoadableState<List<MainSection>>> = _mainSectionsLiveData

    /** Видимость кнопки дебага */
    private val _isDebugButtonVisibleLiveData = MutableLiveData<LoadableState<Boolean>>()
    val isDebugButtonVisibleLiveData: LiveData<LoadableState<Boolean>> = _isDebugButtonVisibleLiveData

    /** Состояние скрола вложенных скролящихся элементов в списке контента */
    val scrollState = ScrollStateHolder()

    fun loadMainSections() {
        scrollState.clearScrollState()
        _mainSectionsLiveData.launchLoadData(getMainSectionsUseCase.executeFlow(Unit))
    }

    fun initDebugButton() {
        _isDebugButtonVisibleLiveData.launchLoadData(isDebugButtonVisibleUseCase.executeFlow(Unit))
    }

    fun openDebug() {
        navigate(destinations.debug())
    }

    fun openCompetition(item: CompetitionItemShort) {
        navigate(destinations.competitionItem(item.id))
    }

    fun openTaglineContent(tagline: Tagline) {
        if (tagline.url == null) return

        val uri = try {
            Uri.parse(tagline.url)
        } catch (e: Exception) {
            null
        } ?: return

        if (isCurrentDomain(uri.host)) {
            deepLinkManager.resolveDeepLink(uri)?.let { destination ->
                navigate(destination)
            } ?: run {
                navigate(destinations.webPage(uri.pathAndQuery()))
            }
        } else {
            navigate(destinations.browser(uri))
        }
    }

    fun resolveDeepLink() {
        deepLinkManager.resolveDeepLink()?.let { navigate(it) }
    }

    private fun isCurrentDomain(host: String?): Boolean {
        return listOf(
            "erudyt-online.ru",
            "erudit-online.ru",
            "erudyt-online.ru",
        ).contains(host)
    }

    private fun Uri.pathAndQuery(): String {
        return buildString {
            append(path)
            if (!query.isNullOrEmpty()) {
                append("&")
                append(query)
            }
        }
    }
}
