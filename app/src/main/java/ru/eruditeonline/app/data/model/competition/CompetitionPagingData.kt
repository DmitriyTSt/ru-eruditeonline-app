package ru.eruditeonline.app.data.model.competition

class CompetitionPagingData(
    val list: List<CompetitionItemShort>,
    val filters: CompetitionFilters?,
    val hasMore: Boolean,
)
