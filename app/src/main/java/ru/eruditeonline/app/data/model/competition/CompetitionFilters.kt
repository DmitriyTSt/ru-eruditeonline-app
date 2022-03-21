package ru.eruditeonline.app.data.model.competition

class CompetitionFilters(
    /** Список фильтрации по возрасту */
    val ages: List<FilterItem>,
    /** Список фильтрации по предметам */
    val subjects: List<FilterItem>,
)
