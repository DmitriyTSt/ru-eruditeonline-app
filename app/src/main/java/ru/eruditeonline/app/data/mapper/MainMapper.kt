package ru.eruditeonline.app.data.mapper

import ru.eruditeonline.app.data.model.main.CompetitionViewType
import ru.eruditeonline.app.data.model.main.MainSection
import ru.eruditeonline.app.data.model.main.Tagline
import ru.eruditeonline.app.data.remote.model.main.ApiCompetitionViewType
import ru.eruditeonline.app.data.remote.model.main.ApiMainSection
import ru.eruditeonline.app.data.remote.model.main.ApiMainSectionType
import ru.eruditeonline.app.data.remote.model.main.ApiTagline
import javax.inject.Inject

class MainMapper @Inject constructor(
    private val competitionMapper: CompetitionMapper,
) {

    fun fromApiToModel(api: ApiMainSection): MainSection? {
        return when (api.type) {
            ApiMainSectionType.TAGLINE -> MainSection.TaglineBlock(
                taglines = api.taglines.orEmpty().map { fromApiToModel(it) },
            )
            ApiMainSectionType.COMPETITIONS -> MainSection.CompetitionsBlock(
                title = api.title.orEmpty(),
                viewType = viewTypeMapper[api.viewType] ?: CompetitionViewType.ROW,
                events = api.events.orEmpty().map { competitionMapper.fromApiToModel(it) },
            )
            null -> null
        }
    }

    private val viewTypeMapper = mapOf(
        ApiCompetitionViewType.CARD to CompetitionViewType.CARD,
        ApiCompetitionViewType.ROW to CompetitionViewType.ROW,
    )

    private fun fromApiToModel(api: ApiTagline): Tagline {
        return Tagline(
            title = api.title.orEmpty(),
            text = api.text.orEmpty(),
            icon = api.icon.orEmpty(),
        )
    }
}
