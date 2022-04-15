package ru.eruditeonline.app.data.model.rating

import ru.eruditeonline.app.data.model.Similarable

data class RatingRow(
    /** Место */
    val rank: Int,
    /** Участник */
    val username: String,
    /** Баллы */
    val score: Int,
    /** Ссылка на иконку страны */
    val countryIcon: String,
    /** Прошлое место */
    val oldRank: Int?,
) : Similarable<RatingRow> {
    override fun areItemsTheSame(other: RatingRow): Boolean {
        return this.rank == other.rank
    }

    override fun areContentsTheSame(other: RatingRow): Boolean {
        return this == other
    }
}
