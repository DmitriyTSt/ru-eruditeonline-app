package ru.eruditeonline.app.data.repository

import ru.eruditeonline.app.data.model.rating.RatingRow

interface RatingRepository {
    /** Получение рейтинга */
    suspend fun getRating(day: Int?, month: Int?, year: Int): List<RatingRow>
}
