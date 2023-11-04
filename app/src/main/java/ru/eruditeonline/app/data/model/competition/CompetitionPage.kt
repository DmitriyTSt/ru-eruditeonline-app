package ru.eruditeonline.app.data.model.competition

class CompetitionPage(
    val list: List<CompetitionItemShort>,
    val filters: CompetitionFilters?,
    val hasMore: Boolean,
)
