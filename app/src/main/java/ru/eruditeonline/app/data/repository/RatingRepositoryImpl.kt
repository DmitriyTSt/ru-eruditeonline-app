package ru.eruditeonline.app.data.repository

import ru.eruditeonline.app.data.mapper.RatingMapper
import ru.eruditeonline.app.data.model.rating.RatingRow
import ru.eruditeonline.app.data.remote.ApiService
import ru.eruditeonline.app.data.remote.params.RatingParams
import javax.inject.Inject

class RatingRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val ratingMapper: RatingMapper,
) : RatingRepository {
    override suspend fun getRating(day: Int?, month: Int?, year: Int): List<RatingRow> {
        return apiService.getRating(RatingParams(day, month, year))
            .data
            ?.list
            ?.map { ratingMapper.fromApiToModel(it) }
            .orEmpty()
    }
}
