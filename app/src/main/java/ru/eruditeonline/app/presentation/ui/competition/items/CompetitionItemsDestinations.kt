package ru.eruditeonline.app.presentation.ui.competition.items

import ru.eruditeonline.app.data.model.competition.CompetitionFilters
import ru.eruditeonline.app.presentation.navigation.Destination

interface CompetitionItemsDestinations {
    /** Фильтр */
    fun filter(filter: CompetitionFilters): Destination

    /** Конкурс */
    fun competitionItem(id: Int): Destination
}
