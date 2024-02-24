package ru.eruditeonline.app.data.mapper

import ru.eruditeonline.app.data.model.rating.RatingRow
import ru.eruditeonline.app.data.remote.model.rating.ApiRatingRow
import ru.eruditeonline.core.domain.ext.orDefault
import javax.inject.Inject

class RatingMapper @Inject constructor() {

    fun fromApiToModel(api: ApiRatingRow): RatingRow {
        return RatingRow(
            rank = api.rank.orDefault(),
            username = api.username.orEmpty(),
            score = api.score.orDefault(),
            countryIcon = api.countryIcon.orEmpty(),
            oldRank = api.oldRank,
        )
    }
}
