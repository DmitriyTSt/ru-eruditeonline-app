package ru.eruditeonline.app.data.mapper

import ru.eruditeonline.app.data.model.competition.CompetitionFilters
import ru.eruditeonline.app.data.model.competition.CompetitionItem
import ru.eruditeonline.app.data.model.competition.CompetitionItemShort
import ru.eruditeonline.app.data.model.competition.CompetitionPage
import ru.eruditeonline.app.data.model.competition.FilterItem
import ru.eruditeonline.app.data.model.competition.TestAgeGroup
import ru.eruditeonline.app.data.remote.model.competition.ApiCompetitionFilters
import ru.eruditeonline.app.data.remote.model.competition.ApiCompetitionItem
import ru.eruditeonline.app.data.remote.model.competition.ApiCompetitionItemShort
import ru.eruditeonline.app.data.remote.model.competition.ApiFilterItem
import ru.eruditeonline.app.data.remote.model.competition.ApiTestAgeGroup
import ru.eruditeonline.app.data.remote.response.CompetitionItemsData
import javax.inject.Inject

class CompetitionMapper @Inject constructor(
    private val baseMapper: BaseMapper,
) {
    fun fromApiToModel(api: ApiCompetitionItemShort): CompetitionItemShort {
        return CompetitionItemShort(
            id = api.id.orDefault(),
            title = api.title.orEmpty(),
            subject = api.subject.orEmpty(),
            ages = api.ages.orEmpty(),
            difficulty = api.difficulty.orDefault(),
            icon = api.icon,
        )
    }

    fun fromApiToModel(api: ApiCompetitionFilters): CompetitionFilters {
        return CompetitionFilters(
            ages = api.ages.orEmpty().map { fromApiToModel(it) },
            subjects = api.subjects.orEmpty().map { fromApiToModel(it) },
        )
    }

    fun fromApiToModel(api: CompetitionItemsData): CompetitionPage {
        return CompetitionPage(
            list = api.list.orEmpty().map { fromApiToModel(it) },
            filters = api.filters?.let { fromApiToModel(it) },
            hasMore = api.hasMore.orDefault()
        )
    }

    fun fromApiToModel(api: ApiCompetitionItem): CompetitionItem {
        return CompetitionItem(
            id = api.id.orDefault(),
            title = api.title.orEmpty(),
            subject = api.subject.orEmpty(),
            ages = api.ages.orEmpty(),
            icon = api.icon,
            difficulty = api.difficulty.orDefault(),
            tests = api.tests.orEmpty().map { fromApiToModel(it) },
            annotation = api.annotation,
            description = api.description,
            infos = api.infos.orEmpty(),
        )
    }

    private fun fromApiToModel(api: ApiFilterItem): FilterItem {
        return FilterItem(
            id = api.id.orEmpty(),
            title = api.title.orEmpty(),
            selected = api.selected.orDefault(),
        )
    }

    private fun fromApiToModel(api: ApiTestAgeGroup): TestAgeGroup {
        return TestAgeGroup(
            id = api.id.orEmpty(),
            title = api.title.orEmpty(),
            color = baseMapper.parseColor(api.color),
            icon = api.icon,
        )
    }
}
