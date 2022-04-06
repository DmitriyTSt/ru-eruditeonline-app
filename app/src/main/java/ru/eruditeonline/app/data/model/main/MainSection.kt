package ru.eruditeonline.app.data.model.main

import ru.eruditeonline.app.data.model.competition.CompetitionItemShort

sealed class MainSection {
    class TaglineBlock(
        val taglines: List<Tagline>,
    ) : MainSection()

    class CompetitionsBlock(
        val title: String,
        val viewType: CompetitionViewType,
        val items: List<CompetitionItemShort>,
    ) : MainSection()
}
