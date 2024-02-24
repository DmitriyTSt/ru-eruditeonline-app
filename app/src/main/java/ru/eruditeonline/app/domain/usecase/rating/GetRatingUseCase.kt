package ru.eruditeonline.app.domain.usecase.rating

import ru.eruditeonline.app.data.model.rating.RatingRow
import ru.eruditeonline.app.data.repository.RatingRepository
import ru.eruditeonline.usecase.UseCaseUnary
import javax.inject.Inject

/**
 * Получение рейтинга
 */
class GetRatingUseCase @Inject constructor(
    private val ratingRepository: RatingRepository,
) : UseCaseUnary<GetRatingUseCase.Params, List<RatingRow>> {

    override suspend fun execute(params: Params): List<RatingRow> {
        return ratingRepository.getRating(params.day, params.month, params.year)
    }

    data class Params(
        val year: Int,
        val month: Int? = null,
        val day: Int? = null,
    )
}
